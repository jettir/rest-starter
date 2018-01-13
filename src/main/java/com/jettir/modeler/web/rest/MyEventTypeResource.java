package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyEventTypeService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyEventTypeDTO;
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
 * REST controller for managing MyEventType.
 */
@RestController
@RequestMapping("/api")
public class MyEventTypeResource {

    private final Logger log = LoggerFactory.getLogger(MyEventTypeResource.class);

    private static final String ENTITY_NAME = "myEventType";

    private final MyEventTypeService myEventTypeService;

    public MyEventTypeResource(MyEventTypeService myEventTypeService) {
        this.myEventTypeService = myEventTypeService;
    }

    /**
     * POST  /my-event-types : Create a new myEventType.
     *
     * @param myEventTypeDTO the myEventTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myEventTypeDTO, or with status 400 (Bad Request) if the myEventType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-event-types")
    @Timed
    public ResponseEntity<MyEventTypeDTO> createMyEventType(@RequestBody MyEventTypeDTO myEventTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MyEventType : {}", myEventTypeDTO);
        if (myEventTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new myEventType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyEventTypeDTO result = myEventTypeService.save(myEventTypeDTO);
        return ResponseEntity.created(new URI("/api/my-event-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-event-types : Updates an existing myEventType.
     *
     * @param myEventTypeDTO the myEventTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myEventTypeDTO,
     * or with status 400 (Bad Request) if the myEventTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the myEventTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-event-types")
    @Timed
    public ResponseEntity<MyEventTypeDTO> updateMyEventType(@RequestBody MyEventTypeDTO myEventTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MyEventType : {}", myEventTypeDTO);
        if (myEventTypeDTO.getId() == null) {
            return createMyEventType(myEventTypeDTO);
        }
        MyEventTypeDTO result = myEventTypeService.save(myEventTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myEventTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-event-types : get all the myEventTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myEventTypes in body
     */
    @GetMapping("/my-event-types")
    @Timed
    public ResponseEntity<List<MyEventTypeDTO>> getAllMyEventTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyEventTypes");
        Page<MyEventTypeDTO> page = myEventTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-event-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-event-types/:id : get the "id" myEventType.
     *
     * @param id the id of the myEventTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myEventTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-event-types/{id}")
    @Timed
    public ResponseEntity<MyEventTypeDTO> getMyEventType(@PathVariable Long id) {
        log.debug("REST request to get MyEventType : {}", id);
        MyEventTypeDTO myEventTypeDTO = myEventTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myEventTypeDTO));
    }

    /**
     * DELETE  /my-event-types/:id : delete the "id" myEventType.
     *
     * @param id the id of the myEventTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-event-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyEventType(@PathVariable Long id) {
        log.debug("REST request to delete MyEventType : {}", id);
        myEventTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
