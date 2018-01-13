package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyParameterTypeService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyParameterTypeDTO;
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
 * REST controller for managing MyParameterType.
 */
@RestController
@RequestMapping("/api")
public class MyParameterTypeResource {

    private final Logger log = LoggerFactory.getLogger(MyParameterTypeResource.class);

    private static final String ENTITY_NAME = "myParameterType";

    private final MyParameterTypeService myParameterTypeService;

    public MyParameterTypeResource(MyParameterTypeService myParameterTypeService) {
        this.myParameterTypeService = myParameterTypeService;
    }

    /**
     * POST  /my-parameter-types : Create a new myParameterType.
     *
     * @param myParameterTypeDTO the myParameterTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myParameterTypeDTO, or with status 400 (Bad Request) if the myParameterType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-parameter-types")
    @Timed
    public ResponseEntity<MyParameterTypeDTO> createMyParameterType(@RequestBody MyParameterTypeDTO myParameterTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MyParameterType : {}", myParameterTypeDTO);
        if (myParameterTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new myParameterType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyParameterTypeDTO result = myParameterTypeService.save(myParameterTypeDTO);
        return ResponseEntity.created(new URI("/api/my-parameter-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-parameter-types : Updates an existing myParameterType.
     *
     * @param myParameterTypeDTO the myParameterTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myParameterTypeDTO,
     * or with status 400 (Bad Request) if the myParameterTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the myParameterTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-parameter-types")
    @Timed
    public ResponseEntity<MyParameterTypeDTO> updateMyParameterType(@RequestBody MyParameterTypeDTO myParameterTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MyParameterType : {}", myParameterTypeDTO);
        if (myParameterTypeDTO.getId() == null) {
            return createMyParameterType(myParameterTypeDTO);
        }
        MyParameterTypeDTO result = myParameterTypeService.save(myParameterTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myParameterTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-parameter-types : get all the myParameterTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myParameterTypes in body
     */
    @GetMapping("/my-parameter-types")
    @Timed
    public ResponseEntity<List<MyParameterTypeDTO>> getAllMyParameterTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyParameterTypes");
        Page<MyParameterTypeDTO> page = myParameterTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-parameter-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-parameter-types/:id : get the "id" myParameterType.
     *
     * @param id the id of the myParameterTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myParameterTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-parameter-types/{id}")
    @Timed
    public ResponseEntity<MyParameterTypeDTO> getMyParameterType(@PathVariable Long id) {
        log.debug("REST request to get MyParameterType : {}", id);
        MyParameterTypeDTO myParameterTypeDTO = myParameterTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myParameterTypeDTO));
    }

    /**
     * DELETE  /my-parameter-types/:id : delete the "id" myParameterType.
     *
     * @param id the id of the myParameterTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-parameter-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyParameterType(@PathVariable Long id) {
        log.debug("REST request to delete MyParameterType : {}", id);
        myParameterTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
