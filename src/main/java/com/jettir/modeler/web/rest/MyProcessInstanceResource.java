package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyProcessInstanceService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyProcessInstanceDTO;
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
 * REST controller for managing MyProcessInstance.
 */
@RestController
@RequestMapping("/api")
public class MyProcessInstanceResource {

    private final Logger log = LoggerFactory.getLogger(MyProcessInstanceResource.class);

    private static final String ENTITY_NAME = "myProcessInstance";

    private final MyProcessInstanceService myProcessInstanceService;

    public MyProcessInstanceResource(MyProcessInstanceService myProcessInstanceService) {
        this.myProcessInstanceService = myProcessInstanceService;
    }

    /**
     * POST  /my-process-instances : Create a new myProcessInstance.
     *
     * @param myProcessInstanceDTO the myProcessInstanceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myProcessInstanceDTO, or with status 400 (Bad Request) if the myProcessInstance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-process-instances")
    @Timed
    public ResponseEntity<MyProcessInstanceDTO> createMyProcessInstance(@RequestBody MyProcessInstanceDTO myProcessInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save MyProcessInstance : {}", myProcessInstanceDTO);
        if (myProcessInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new myProcessInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyProcessInstanceDTO result = myProcessInstanceService.save(myProcessInstanceDTO);
        return ResponseEntity.created(new URI("/api/my-process-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-process-instances : Updates an existing myProcessInstance.
     *
     * @param myProcessInstanceDTO the myProcessInstanceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myProcessInstanceDTO,
     * or with status 400 (Bad Request) if the myProcessInstanceDTO is not valid,
     * or with status 500 (Internal Server Error) if the myProcessInstanceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-process-instances")
    @Timed
    public ResponseEntity<MyProcessInstanceDTO> updateMyProcessInstance(@RequestBody MyProcessInstanceDTO myProcessInstanceDTO) throws URISyntaxException {
        log.debug("REST request to update MyProcessInstance : {}", myProcessInstanceDTO);
        if (myProcessInstanceDTO.getId() == null) {
            return createMyProcessInstance(myProcessInstanceDTO);
        }
        MyProcessInstanceDTO result = myProcessInstanceService.save(myProcessInstanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myProcessInstanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-process-instances : get all the myProcessInstances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myProcessInstances in body
     */
    @GetMapping("/my-process-instances")
    @Timed
    public ResponseEntity<List<MyProcessInstanceDTO>> getAllMyProcessInstances(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyProcessInstances");
        Page<MyProcessInstanceDTO> page = myProcessInstanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-process-instances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-process-instances/:id : get the "id" myProcessInstance.
     *
     * @param id the id of the myProcessInstanceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myProcessInstanceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-process-instances/{id}")
    @Timed
    public ResponseEntity<MyProcessInstanceDTO> getMyProcessInstance(@PathVariable Long id) {
        log.debug("REST request to get MyProcessInstance : {}", id);
        MyProcessInstanceDTO myProcessInstanceDTO = myProcessInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myProcessInstanceDTO));
    }

    /**
     * DELETE  /my-process-instances/:id : delete the "id" myProcessInstance.
     *
     * @param id the id of the myProcessInstanceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-process-instances/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyProcessInstance(@PathVariable Long id) {
        log.debug("REST request to delete MyProcessInstance : {}", id);
        myProcessInstanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
