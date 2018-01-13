package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyTaskService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyTaskDTO;
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
 * REST controller for managing MyTask.
 */
@RestController
@RequestMapping("/api")
public class MyTaskResource {

    private final Logger log = LoggerFactory.getLogger(MyTaskResource.class);

    private static final String ENTITY_NAME = "myTask";

    private final MyTaskService myTaskService;

    public MyTaskResource(MyTaskService myTaskService) {
        this.myTaskService = myTaskService;
    }

    /**
     * POST  /my-tasks : Create a new myTask.
     *
     * @param myTaskDTO the myTaskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myTaskDTO, or with status 400 (Bad Request) if the myTask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-tasks")
    @Timed
    public ResponseEntity<MyTaskDTO> createMyTask(@RequestBody MyTaskDTO myTaskDTO) throws URISyntaxException {
        log.debug("REST request to save MyTask : {}", myTaskDTO);
        if (myTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new myTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyTaskDTO result = myTaskService.save(myTaskDTO);
        return ResponseEntity.created(new URI("/api/my-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-tasks : Updates an existing myTask.
     *
     * @param myTaskDTO the myTaskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myTaskDTO,
     * or with status 400 (Bad Request) if the myTaskDTO is not valid,
     * or with status 500 (Internal Server Error) if the myTaskDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-tasks")
    @Timed
    public ResponseEntity<MyTaskDTO> updateMyTask(@RequestBody MyTaskDTO myTaskDTO) throws URISyntaxException {
        log.debug("REST request to update MyTask : {}", myTaskDTO);
        if (myTaskDTO.getId() == null) {
            return createMyTask(myTaskDTO);
        }
        MyTaskDTO result = myTaskService.save(myTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-tasks : get all the myTasks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myTasks in body
     */
    @GetMapping("/my-tasks")
    @Timed
    public ResponseEntity<List<MyTaskDTO>> getAllMyTasks(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyTasks");
        Page<MyTaskDTO> page = myTaskService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-tasks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-tasks/:id : get the "id" myTask.
     *
     * @param id the id of the myTaskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myTaskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-tasks/{id}")
    @Timed
    public ResponseEntity<MyTaskDTO> getMyTask(@PathVariable Long id) {
        log.debug("REST request to get MyTask : {}", id);
        MyTaskDTO myTaskDTO = myTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myTaskDTO));
    }

    /**
     * DELETE  /my-tasks/:id : delete the "id" myTask.
     *
     * @param id the id of the myTaskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-tasks/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyTask(@PathVariable Long id) {
        log.debug("REST request to delete MyTask : {}", id);
        myTaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
