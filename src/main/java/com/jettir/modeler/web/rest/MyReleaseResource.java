package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyReleaseService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyReleaseDTO;
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
 * REST controller for managing MyRelease.
 */
@RestController
@RequestMapping("/api")
public class MyReleaseResource {

    private final Logger log = LoggerFactory.getLogger(MyReleaseResource.class);

    private static final String ENTITY_NAME = "myRelease";

    private final MyReleaseService myReleaseService;

    public MyReleaseResource(MyReleaseService myReleaseService) {
        this.myReleaseService = myReleaseService;
    }

    /**
     * POST  /my-releases : Create a new myRelease.
     *
     * @param myReleaseDTO the myReleaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myReleaseDTO, or with status 400 (Bad Request) if the myRelease has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-releases")
    @Timed
    public ResponseEntity<MyReleaseDTO> createMyRelease(@RequestBody MyReleaseDTO myReleaseDTO) throws URISyntaxException {
        log.debug("REST request to save MyRelease : {}", myReleaseDTO);
        if (myReleaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new myRelease cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyReleaseDTO result = myReleaseService.save(myReleaseDTO);
        return ResponseEntity.created(new URI("/api/my-releases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-releases : Updates an existing myRelease.
     *
     * @param myReleaseDTO the myReleaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myReleaseDTO,
     * or with status 400 (Bad Request) if the myReleaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the myReleaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-releases")
    @Timed
    public ResponseEntity<MyReleaseDTO> updateMyRelease(@RequestBody MyReleaseDTO myReleaseDTO) throws URISyntaxException {
        log.debug("REST request to update MyRelease : {}", myReleaseDTO);
        if (myReleaseDTO.getId() == null) {
            return createMyRelease(myReleaseDTO);
        }
        MyReleaseDTO result = myReleaseService.save(myReleaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myReleaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-releases : get all the myReleases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myReleases in body
     */
    @GetMapping("/my-releases")
    @Timed
    public ResponseEntity<List<MyReleaseDTO>> getAllMyReleases(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyReleases");
        Page<MyReleaseDTO> page = myReleaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-releases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-releases/:id : get the "id" myRelease.
     *
     * @param id the id of the myReleaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myReleaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-releases/{id}")
    @Timed
    public ResponseEntity<MyReleaseDTO> getMyRelease(@PathVariable Long id) {
        log.debug("REST request to get MyRelease : {}", id);
        MyReleaseDTO myReleaseDTO = myReleaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myReleaseDTO));
    }

    /**
     * DELETE  /my-releases/:id : delete the "id" myRelease.
     *
     * @param id the id of the myReleaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-releases/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyRelease(@PathVariable Long id) {
        log.debug("REST request to delete MyRelease : {}", id);
        myReleaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
