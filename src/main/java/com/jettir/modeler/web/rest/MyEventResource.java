package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyEventService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyEventDTO;
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
 * REST controller for managing MyEvent.
 */
@RestController
@RequestMapping("/api")
public class MyEventResource {

    private final Logger log = LoggerFactory.getLogger(MyEventResource.class);

    private static final String ENTITY_NAME = "myEvent";

    private final MyEventService myEventService;

    public MyEventResource(MyEventService myEventService) {
        this.myEventService = myEventService;
    }

    /**
     * POST  /my-events : Create a new myEvent.
     *
     * @param myEventDTO the myEventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myEventDTO, or with status 400 (Bad Request) if the myEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-events")
    @Timed
    public ResponseEntity<MyEventDTO> createMyEvent(@RequestBody MyEventDTO myEventDTO) throws URISyntaxException {
        log.debug("REST request to save MyEvent : {}", myEventDTO);
        if (myEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new myEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyEventDTO result = myEventService.save(myEventDTO);
        return ResponseEntity.created(new URI("/api/my-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-events : Updates an existing myEvent.
     *
     * @param myEventDTO the myEventDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myEventDTO,
     * or with status 400 (Bad Request) if the myEventDTO is not valid,
     * or with status 500 (Internal Server Error) if the myEventDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-events")
    @Timed
    public ResponseEntity<MyEventDTO> updateMyEvent(@RequestBody MyEventDTO myEventDTO) throws URISyntaxException {
        log.debug("REST request to update MyEvent : {}", myEventDTO);
        if (myEventDTO.getId() == null) {
            return createMyEvent(myEventDTO);
        }
        MyEventDTO result = myEventService.save(myEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-events : get all the myEvents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myEvents in body
     */
    @GetMapping("/my-events")
    @Timed
    public ResponseEntity<List<MyEventDTO>> getAllMyEvents(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyEvents");
        Page<MyEventDTO> page = myEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-events/:id : get the "id" myEvent.
     *
     * @param id the id of the myEventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myEventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-events/{id}")
    @Timed
    public ResponseEntity<MyEventDTO> getMyEvent(@PathVariable Long id) {
        log.debug("REST request to get MyEvent : {}", id);
        MyEventDTO myEventDTO = myEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myEventDTO));
    }

    /**
     * DELETE  /my-events/:id : delete the "id" myEvent.
     *
     * @param id the id of the myEventDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyEvent(@PathVariable Long id) {
        log.debug("REST request to delete MyEvent : {}", id);
        myEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
