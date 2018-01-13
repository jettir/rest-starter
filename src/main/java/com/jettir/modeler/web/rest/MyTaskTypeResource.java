package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyTaskTypeService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyTaskTypeDTO;
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
 * REST controller for managing MyTaskType.
 */
@RestController
@RequestMapping("/api")
public class MyTaskTypeResource {

    private final Logger log = LoggerFactory.getLogger(MyTaskTypeResource.class);

    private static final String ENTITY_NAME = "myTaskType";

    private final MyTaskTypeService myTaskTypeService;

    public MyTaskTypeResource(MyTaskTypeService myTaskTypeService) {
        this.myTaskTypeService = myTaskTypeService;
    }

    /**
     * POST  /my-task-types : Create a new myTaskType.
     *
     * @param myTaskTypeDTO the myTaskTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myTaskTypeDTO, or with status 400 (Bad Request) if the myTaskType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-task-types")
    @Timed
    public ResponseEntity<MyTaskTypeDTO> createMyTaskType(@RequestBody MyTaskTypeDTO myTaskTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MyTaskType : {}", myTaskTypeDTO);
        if (myTaskTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new myTaskType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyTaskTypeDTO result = myTaskTypeService.save(myTaskTypeDTO);
        return ResponseEntity.created(new URI("/api/my-task-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-task-types : Updates an existing myTaskType.
     *
     * @param myTaskTypeDTO the myTaskTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myTaskTypeDTO,
     * or with status 400 (Bad Request) if the myTaskTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the myTaskTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-task-types")
    @Timed
    public ResponseEntity<MyTaskTypeDTO> updateMyTaskType(@RequestBody MyTaskTypeDTO myTaskTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MyTaskType : {}", myTaskTypeDTO);
        if (myTaskTypeDTO.getId() == null) {
            return createMyTaskType(myTaskTypeDTO);
        }
        MyTaskTypeDTO result = myTaskTypeService.save(myTaskTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myTaskTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-task-types : get all the myTaskTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myTaskTypes in body
     */
    @GetMapping("/my-task-types")
    @Timed
    public ResponseEntity<List<MyTaskTypeDTO>> getAllMyTaskTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyTaskTypes");
        Page<MyTaskTypeDTO> page = myTaskTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-task-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-task-types/:id : get the "id" myTaskType.
     *
     * @param id the id of the myTaskTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myTaskTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-task-types/{id}")
    @Timed
    public ResponseEntity<MyTaskTypeDTO> getMyTaskType(@PathVariable Long id) {
        log.debug("REST request to get MyTaskType : {}", id);
        MyTaskTypeDTO myTaskTypeDTO = myTaskTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myTaskTypeDTO));
    }

    /**
     * DELETE  /my-task-types/:id : delete the "id" myTaskType.
     *
     * @param id the id of the myTaskTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-task-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyTaskType(@PathVariable Long id) {
        log.debug("REST request to delete MyTaskType : {}", id);
        myTaskTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
