package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyEntityService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyEntityDTO;
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
 * REST controller for managing MyEntity.
 */
@RestController
@RequestMapping("/api")
public class MyEntityResource {

    private final Logger log = LoggerFactory.getLogger(MyEntityResource.class);

    private static final String ENTITY_NAME = "myEntity";

    private final MyEntityService myEntityService;

    public MyEntityResource(MyEntityService myEntityService) {
        this.myEntityService = myEntityService;
    }

    /**
     * POST  /my-entities : Create a new myEntity.
     *
     * @param myEntityDTO the myEntityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myEntityDTO, or with status 400 (Bad Request) if the myEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-entities")
    @Timed
    public ResponseEntity<MyEntityDTO> createMyEntity(@RequestBody MyEntityDTO myEntityDTO) throws URISyntaxException {
        log.debug("REST request to save MyEntity : {}", myEntityDTO);
        if (myEntityDTO.getId() != null) {
            throw new BadRequestAlertException("A new myEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyEntityDTO result = myEntityService.save(myEntityDTO);
        return ResponseEntity.created(new URI("/api/my-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-entities : Updates an existing myEntity.
     *
     * @param myEntityDTO the myEntityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myEntityDTO,
     * or with status 400 (Bad Request) if the myEntityDTO is not valid,
     * or with status 500 (Internal Server Error) if the myEntityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-entities")
    @Timed
    public ResponseEntity<MyEntityDTO> updateMyEntity(@RequestBody MyEntityDTO myEntityDTO) throws URISyntaxException {
        log.debug("REST request to update MyEntity : {}", myEntityDTO);
        if (myEntityDTO.getId() == null) {
            return createMyEntity(myEntityDTO);
        }
        MyEntityDTO result = myEntityService.save(myEntityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myEntityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-entities : get all the myEntities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myEntities in body
     */
    @GetMapping("/my-entities")
    @Timed
    public ResponseEntity<List<MyEntityDTO>> getAllMyEntities(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyEntities");
        Page<MyEntityDTO> page = myEntityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-entities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-entities/:id : get the "id" myEntity.
     *
     * @param id the id of the myEntityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myEntityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-entities/{id}")
    @Timed
    public ResponseEntity<MyEntityDTO> getMyEntity(@PathVariable Long id) {
        log.debug("REST request to get MyEntity : {}", id);
        MyEntityDTO myEntityDTO = myEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myEntityDTO));
    }

    /**
     * DELETE  /my-entities/:id : delete the "id" myEntity.
     *
     * @param id the id of the myEntityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyEntity(@PathVariable Long id) {
        log.debug("REST request to delete MyEntity : {}", id);
        myEntityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
