package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyEntityTypeService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyEntityTypeDTO;
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
 * REST controller for managing MyEntityType.
 */
@RestController
@RequestMapping("/api")
public class MyEntityTypeResource {

    private final Logger log = LoggerFactory.getLogger(MyEntityTypeResource.class);

    private static final String ENTITY_NAME = "myEntityType";

    private final MyEntityTypeService myEntityTypeService;

    public MyEntityTypeResource(MyEntityTypeService myEntityTypeService) {
        this.myEntityTypeService = myEntityTypeService;
    }

    /**
     * POST  /my-entity-types : Create a new myEntityType.
     *
     * @param myEntityTypeDTO the myEntityTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myEntityTypeDTO, or with status 400 (Bad Request) if the myEntityType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-entity-types")
    @Timed
    public ResponseEntity<MyEntityTypeDTO> createMyEntityType(@RequestBody MyEntityTypeDTO myEntityTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MyEntityType : {}", myEntityTypeDTO);
        if (myEntityTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new myEntityType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyEntityTypeDTO result = myEntityTypeService.save(myEntityTypeDTO);
        return ResponseEntity.created(new URI("/api/my-entity-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-entity-types : Updates an existing myEntityType.
     *
     * @param myEntityTypeDTO the myEntityTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myEntityTypeDTO,
     * or with status 400 (Bad Request) if the myEntityTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the myEntityTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-entity-types")
    @Timed
    public ResponseEntity<MyEntityTypeDTO> updateMyEntityType(@RequestBody MyEntityTypeDTO myEntityTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MyEntityType : {}", myEntityTypeDTO);
        if (myEntityTypeDTO.getId() == null) {
            return createMyEntityType(myEntityTypeDTO);
        }
        MyEntityTypeDTO result = myEntityTypeService.save(myEntityTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myEntityTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-entity-types : get all the myEntityTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myEntityTypes in body
     */
    @GetMapping("/my-entity-types")
    @Timed
    public ResponseEntity<List<MyEntityTypeDTO>> getAllMyEntityTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyEntityTypes");
        Page<MyEntityTypeDTO> page = myEntityTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-entity-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-entity-types/:id : get the "id" myEntityType.
     *
     * @param id the id of the myEntityTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myEntityTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-entity-types/{id}")
    @Timed
    public ResponseEntity<MyEntityTypeDTO> getMyEntityType(@PathVariable Long id) {
        log.debug("REST request to get MyEntityType : {}", id);
        MyEntityTypeDTO myEntityTypeDTO = myEntityTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myEntityTypeDTO));
    }

    /**
     * DELETE  /my-entity-types/:id : delete the "id" myEntityType.
     *
     * @param id the id of the myEntityTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-entity-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyEntityType(@PathVariable Long id) {
        log.debug("REST request to delete MyEntityType : {}", id);
        myEntityTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
