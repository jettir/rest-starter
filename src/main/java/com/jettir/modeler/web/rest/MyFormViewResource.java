package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyFormViewService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyFormViewDTO;
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
 * REST controller for managing MyFormView.
 */
@RestController
@RequestMapping("/api")
public class MyFormViewResource {

    private final Logger log = LoggerFactory.getLogger(MyFormViewResource.class);

    private static final String ENTITY_NAME = "myFormView";

    private final MyFormViewService myFormViewService;

    public MyFormViewResource(MyFormViewService myFormViewService) {
        this.myFormViewService = myFormViewService;
    }

    /**
     * POST  /my-form-views : Create a new myFormView.
     *
     * @param myFormViewDTO the myFormViewDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myFormViewDTO, or with status 400 (Bad Request) if the myFormView has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-form-views")
    @Timed
    public ResponseEntity<MyFormViewDTO> createMyFormView(@RequestBody MyFormViewDTO myFormViewDTO) throws URISyntaxException {
        log.debug("REST request to save MyFormView : {}", myFormViewDTO);
        if (myFormViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new myFormView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyFormViewDTO result = myFormViewService.save(myFormViewDTO);
        return ResponseEntity.created(new URI("/api/my-form-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-form-views : Updates an existing myFormView.
     *
     * @param myFormViewDTO the myFormViewDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myFormViewDTO,
     * or with status 400 (Bad Request) if the myFormViewDTO is not valid,
     * or with status 500 (Internal Server Error) if the myFormViewDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-form-views")
    @Timed
    public ResponseEntity<MyFormViewDTO> updateMyFormView(@RequestBody MyFormViewDTO myFormViewDTO) throws URISyntaxException {
        log.debug("REST request to update MyFormView : {}", myFormViewDTO);
        if (myFormViewDTO.getId() == null) {
            return createMyFormView(myFormViewDTO);
        }
        MyFormViewDTO result = myFormViewService.save(myFormViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myFormViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-form-views : get all the myFormViews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myFormViews in body
     */
    @GetMapping("/my-form-views")
    @Timed
    public ResponseEntity<List<MyFormViewDTO>> getAllMyFormViews(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyFormViews");
        Page<MyFormViewDTO> page = myFormViewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-form-views");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-form-views/:id : get the "id" myFormView.
     *
     * @param id the id of the myFormViewDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myFormViewDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-form-views/{id}")
    @Timed
    public ResponseEntity<MyFormViewDTO> getMyFormView(@PathVariable Long id) {
        log.debug("REST request to get MyFormView : {}", id);
        MyFormViewDTO myFormViewDTO = myFormViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myFormViewDTO));
    }

    /**
     * DELETE  /my-form-views/:id : delete the "id" myFormView.
     *
     * @param id the id of the myFormViewDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-form-views/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyFormView(@PathVariable Long id) {
        log.debug("REST request to delete MyFormView : {}", id);
        myFormViewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
