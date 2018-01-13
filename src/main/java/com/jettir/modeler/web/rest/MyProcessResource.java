package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyProcessService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyProcessDTO;
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
 * REST controller for managing MyProcess.
 */
@RestController
@RequestMapping("/api")
public class MyProcessResource {

    private final Logger log = LoggerFactory.getLogger(MyProcessResource.class);

    private static final String ENTITY_NAME = "myProcess";

    private final MyProcessService myProcessService;

    public MyProcessResource(MyProcessService myProcessService) {
        this.myProcessService = myProcessService;
    }

    /**
     * POST  /my-processes : Create a new myProcess.
     *
     * @param myProcessDTO the myProcessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myProcessDTO, or with status 400 (Bad Request) if the myProcess has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-processes")
    @Timed
    public ResponseEntity<MyProcessDTO> createMyProcess(@RequestBody MyProcessDTO myProcessDTO) throws URISyntaxException {
        log.debug("REST request to save MyProcess : {}", myProcessDTO);
        if (myProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new myProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyProcessDTO result = myProcessService.save(myProcessDTO);
        return ResponseEntity.created(new URI("/api/my-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-processes : Updates an existing myProcess.
     *
     * @param myProcessDTO the myProcessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myProcessDTO,
     * or with status 400 (Bad Request) if the myProcessDTO is not valid,
     * or with status 500 (Internal Server Error) if the myProcessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-processes")
    @Timed
    public ResponseEntity<MyProcessDTO> updateMyProcess(@RequestBody MyProcessDTO myProcessDTO) throws URISyntaxException {
        log.debug("REST request to update MyProcess : {}", myProcessDTO);
        if (myProcessDTO.getId() == null) {
            return createMyProcess(myProcessDTO);
        }
        MyProcessDTO result = myProcessService.save(myProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myProcessDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-processes : get all the myProcesses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myProcesses in body
     */
    @GetMapping("/my-processes")
    @Timed
    public ResponseEntity<List<MyProcessDTO>> getAllMyProcesses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyProcesses");
        Page<MyProcessDTO> page = myProcessService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-processes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-processes/:id : get the "id" myProcess.
     *
     * @param id the id of the myProcessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myProcessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-processes/{id}")
    @Timed
    public ResponseEntity<MyProcessDTO> getMyProcess(@PathVariable Long id) {
        log.debug("REST request to get MyProcess : {}", id);
        MyProcessDTO myProcessDTO = myProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myProcessDTO));
    }

    /**
     * DELETE  /my-processes/:id : delete the "id" myProcess.
     *
     * @param id the id of the myProcessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-processes/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyProcess(@PathVariable Long id) {
        log.debug("REST request to delete MyProcess : {}", id);
        myProcessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
