package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyAttributeService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyAttributeDTO;
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
 * REST controller for managing MyAttribute.
 */
@RestController
@RequestMapping("/api")
public class MyAttributeResource {

    private final Logger log = LoggerFactory.getLogger(MyAttributeResource.class);

    private static final String ENTITY_NAME = "myAttribute";

    private final MyAttributeService myAttributeService;

    public MyAttributeResource(MyAttributeService myAttributeService) {
        this.myAttributeService = myAttributeService;
    }

    /**
     * POST  /my-attributes : Create a new myAttribute.
     *
     * @param myAttributeDTO the myAttributeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myAttributeDTO, or with status 400 (Bad Request) if the myAttribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-attributes")
    @Timed
    public ResponseEntity<MyAttributeDTO> createMyAttribute(@RequestBody MyAttributeDTO myAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save MyAttribute : {}", myAttributeDTO);
        if (myAttributeDTO.getId() != null) {
            throw new BadRequestAlertException("A new myAttribute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyAttributeDTO result = myAttributeService.save(myAttributeDTO);
        return ResponseEntity.created(new URI("/api/my-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-attributes : Updates an existing myAttribute.
     *
     * @param myAttributeDTO the myAttributeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myAttributeDTO,
     * or with status 400 (Bad Request) if the myAttributeDTO is not valid,
     * or with status 500 (Internal Server Error) if the myAttributeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-attributes")
    @Timed
    public ResponseEntity<MyAttributeDTO> updateMyAttribute(@RequestBody MyAttributeDTO myAttributeDTO) throws URISyntaxException {
        log.debug("REST request to update MyAttribute : {}", myAttributeDTO);
        if (myAttributeDTO.getId() == null) {
            return createMyAttribute(myAttributeDTO);
        }
        MyAttributeDTO result = myAttributeService.save(myAttributeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myAttributeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-attributes : get all the myAttributes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myAttributes in body
     */
    @GetMapping("/my-attributes")
    @Timed
    public ResponseEntity<List<MyAttributeDTO>> getAllMyAttributes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyAttributes");
        Page<MyAttributeDTO> page = myAttributeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-attributes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-attributes/:id : get the "id" myAttribute.
     *
     * @param id the id of the myAttributeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myAttributeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-attributes/{id}")
    @Timed
    public ResponseEntity<MyAttributeDTO> getMyAttribute(@PathVariable Long id) {
        log.debug("REST request to get MyAttribute : {}", id);
        MyAttributeDTO myAttributeDTO = myAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myAttributeDTO));
    }

    /**
     * DELETE  /my-attributes/:id : delete the "id" myAttribute.
     *
     * @param id the id of the myAttributeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-attributes/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyAttribute(@PathVariable Long id) {
        log.debug("REST request to delete MyAttribute : {}", id);
        myAttributeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
