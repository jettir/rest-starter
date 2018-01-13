package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyBranchService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyBranchDTO;
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
 * REST controller for managing MyBranch.
 */
@RestController
@RequestMapping("/api")
public class MyBranchResource {

    private final Logger log = LoggerFactory.getLogger(MyBranchResource.class);

    private static final String ENTITY_NAME = "myBranch";

    private final MyBranchService myBranchService;

    public MyBranchResource(MyBranchService myBranchService) {
        this.myBranchService = myBranchService;
    }

    /**
     * POST  /my-branches : Create a new myBranch.
     *
     * @param myBranchDTO the myBranchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myBranchDTO, or with status 400 (Bad Request) if the myBranch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-branches")
    @Timed
    public ResponseEntity<MyBranchDTO> createMyBranch(@RequestBody MyBranchDTO myBranchDTO) throws URISyntaxException {
        log.debug("REST request to save MyBranch : {}", myBranchDTO);
        if (myBranchDTO.getId() != null) {
            throw new BadRequestAlertException("A new myBranch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyBranchDTO result = myBranchService.save(myBranchDTO);
        return ResponseEntity.created(new URI("/api/my-branches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-branches : Updates an existing myBranch.
     *
     * @param myBranchDTO the myBranchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myBranchDTO,
     * or with status 400 (Bad Request) if the myBranchDTO is not valid,
     * or with status 500 (Internal Server Error) if the myBranchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-branches")
    @Timed
    public ResponseEntity<MyBranchDTO> updateMyBranch(@RequestBody MyBranchDTO myBranchDTO) throws URISyntaxException {
        log.debug("REST request to update MyBranch : {}", myBranchDTO);
        if (myBranchDTO.getId() == null) {
            return createMyBranch(myBranchDTO);
        }
        MyBranchDTO result = myBranchService.save(myBranchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myBranchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-branches : get all the myBranches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myBranches in body
     */
    @GetMapping("/my-branches")
    @Timed
    public ResponseEntity<List<MyBranchDTO>> getAllMyBranches(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyBranches");
        Page<MyBranchDTO> page = myBranchService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-branches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-branches/:id : get the "id" myBranch.
     *
     * @param id the id of the myBranchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myBranchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-branches/{id}")
    @Timed
    public ResponseEntity<MyBranchDTO> getMyBranch(@PathVariable Long id) {
        log.debug("REST request to get MyBranch : {}", id);
        MyBranchDTO myBranchDTO = myBranchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myBranchDTO));
    }

    /**
     * DELETE  /my-branches/:id : delete the "id" myBranch.
     *
     * @param id the id of the myBranchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-branches/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyBranch(@PathVariable Long id) {
        log.debug("REST request to delete MyBranch : {}", id);
        myBranchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
