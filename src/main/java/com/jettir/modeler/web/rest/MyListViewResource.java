package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyListViewService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyListViewDTO;
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
 * REST controller for managing MyListView.
 */
@RestController
@RequestMapping("/api")
public class MyListViewResource {

    private final Logger log = LoggerFactory.getLogger(MyListViewResource.class);

    private static final String ENTITY_NAME = "myListView";

    private final MyListViewService myListViewService;

    public MyListViewResource(MyListViewService myListViewService) {
        this.myListViewService = myListViewService;
    }

    /**
     * POST  /my-list-views : Create a new myListView.
     *
     * @param myListViewDTO the myListViewDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myListViewDTO, or with status 400 (Bad Request) if the myListView has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-list-views")
    @Timed
    public ResponseEntity<MyListViewDTO> createMyListView(@RequestBody MyListViewDTO myListViewDTO) throws URISyntaxException {
        log.debug("REST request to save MyListView : {}", myListViewDTO);
        if (myListViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new myListView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyListViewDTO result = myListViewService.save(myListViewDTO);
        return ResponseEntity.created(new URI("/api/my-list-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-list-views : Updates an existing myListView.
     *
     * @param myListViewDTO the myListViewDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myListViewDTO,
     * or with status 400 (Bad Request) if the myListViewDTO is not valid,
     * or with status 500 (Internal Server Error) if the myListViewDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-list-views")
    @Timed
    public ResponseEntity<MyListViewDTO> updateMyListView(@RequestBody MyListViewDTO myListViewDTO) throws URISyntaxException {
        log.debug("REST request to update MyListView : {}", myListViewDTO);
        if (myListViewDTO.getId() == null) {
            return createMyListView(myListViewDTO);
        }
        MyListViewDTO result = myListViewService.save(myListViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myListViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-list-views : get all the myListViews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myListViews in body
     */
    @GetMapping("/my-list-views")
    @Timed
    public ResponseEntity<List<MyListViewDTO>> getAllMyListViews(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyListViews");
        Page<MyListViewDTO> page = myListViewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-list-views");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-list-views/:id : get the "id" myListView.
     *
     * @param id the id of the myListViewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myListViewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-list-views/{id}")
    @Timed
    public ResponseEntity<MyListViewDTO> getMyListView(@PathVariable Long id) {
        log.debug("REST request to get MyListView : {}", id);
        MyListViewDTO myListViewDTO = myListViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myListViewDTO));
    }

    /**
     * DELETE  /my-list-views/:id : delete the "id" myListView.
     *
     * @param id the id of the myListViewDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-list-views/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyListView(@PathVariable Long id) {
        log.debug("REST request to delete MyListView : {}", id);
        myListViewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
