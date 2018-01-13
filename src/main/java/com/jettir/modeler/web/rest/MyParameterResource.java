package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyParameterService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyParameterDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MyParameter.
 */
@RestController
@RequestMapping("/api")
public class MyParameterResource {

    private final Logger log = LoggerFactory.getLogger(MyParameterResource.class);

    private static final String ENTITY_NAME = "myParameter";

    private final MyParameterService myParameterService;

    public MyParameterResource(MyParameterService myParameterService) {
        this.myParameterService = myParameterService;
    }

    /**
     * POST  /my-parameters : Create a new myParameter.
     *
     * @param myParameterDTO the myParameterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myParameterDTO, or with status 400 (Bad Request) if the myParameter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-parameters")
    @Timed
    public ResponseEntity<MyParameterDTO> createMyParameter(@RequestBody MyParameterDTO myParameterDTO) throws URISyntaxException {
        log.debug("REST request to save MyParameter : {}", myParameterDTO);
        if (myParameterDTO.getId() != null) {
            throw new BadRequestAlertException("A new myParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyParameterDTO result = myParameterService.save(myParameterDTO);
        return ResponseEntity.created(new URI("/api/my-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-parameters : Updates an existing myParameter.
     *
     * @param myParameterDTO the myParameterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myParameterDTO,
     * or with status 400 (Bad Request) if the myParameterDTO is not valid,
     * or with status 500 (Internal Server Error) if the myParameterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-parameters")
    @Timed
    public ResponseEntity<MyParameterDTO> updateMyParameter(@RequestBody MyParameterDTO myParameterDTO) throws URISyntaxException {
        log.debug("REST request to update MyParameter : {}", myParameterDTO);
        if (myParameterDTO.getId() == null) {
            return createMyParameter(myParameterDTO);
        }
        MyParameterDTO result = myParameterService.save(myParameterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myParameterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-parameters : get all the myParameters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myParameters in body
     */
    @GetMapping("/my-parameters")
    @Timed
    public ResponseEntity<List<MyParameterDTO>> getAllMyParameters(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyParameters");
        Page<MyParameterDTO> page = myParameterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-parameters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-parameters/:id : get the "id" myParameter.
     *
     * @param id the id of the myParameterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myParameterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-parameters/{id}")
    @Timed
    public ResponseEntity<MyParameterDTO> getMyParameter(@PathVariable Long id) {
        log.debug("REST request to get MyParameter : {}", id);
        MyParameterDTO myParameterDTO = myParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myParameterDTO));
    }

    /**
     * DELETE  /my-parameters/:id : delete the "id" myParameter.
     *
     * @param id the id of the myParameterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-parameters/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyParameter(@PathVariable Long id) {
        log.debug("REST request to delete MyParameter : {}", id);
        myParameterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
