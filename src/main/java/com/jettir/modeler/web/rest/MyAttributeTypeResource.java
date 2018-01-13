package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyAttributeTypeService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyAttributeTypeDTO;
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
 * REST controller for managing MyAttributeType.
 */
@RestController
@RequestMapping("/api")
public class MyAttributeTypeResource {

    private final Logger log = LoggerFactory.getLogger(MyAttributeTypeResource.class);

    private static final String ENTITY_NAME = "myAttributeType";

    private final MyAttributeTypeService myAttributeTypeService;

    public MyAttributeTypeResource(MyAttributeTypeService myAttributeTypeService) {
        this.myAttributeTypeService = myAttributeTypeService;
    }

    /**
     * POST  /my-attribute-types : Create a new myAttributeType.
     *
     * @param myAttributeTypeDTO the myAttributeTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myAttributeTypeDTO, or with status 400 (Bad Request) if the myAttributeType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-attribute-types")
    @Timed
    public ResponseEntity<MyAttributeTypeDTO> createMyAttributeType(@RequestBody MyAttributeTypeDTO myAttributeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MyAttributeType : {}", myAttributeTypeDTO);
        if (myAttributeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new myAttributeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyAttributeTypeDTO result = myAttributeTypeService.save(myAttributeTypeDTO);
        return ResponseEntity.created(new URI("/api/my-attribute-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-attribute-types : Updates an existing myAttributeType.
     *
     * @param myAttributeTypeDTO the myAttributeTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myAttributeTypeDTO,
     * or with status 400 (Bad Request) if the myAttributeTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the myAttributeTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-attribute-types")
    @Timed
    public ResponseEntity<MyAttributeTypeDTO> updateMyAttributeType(@RequestBody MyAttributeTypeDTO myAttributeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MyAttributeType : {}", myAttributeTypeDTO);
        if (myAttributeTypeDTO.getId() == null) {
            return createMyAttributeType(myAttributeTypeDTO);
        }
        MyAttributeTypeDTO result = myAttributeTypeService.save(myAttributeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myAttributeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-attribute-types : get all the myAttributeTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myAttributeTypes in body
     */
    @GetMapping("/my-attribute-types")
    @Timed
    public ResponseEntity<List<MyAttributeTypeDTO>> getAllMyAttributeTypes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyAttributeTypes");
        Page<MyAttributeTypeDTO> page = myAttributeTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-attribute-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-attribute-types/:id : get the "id" myAttributeType.
     *
     * @param id the id of the myAttributeTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myAttributeTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-attribute-types/{id}")
    @Timed
    public ResponseEntity<MyAttributeTypeDTO> getMyAttributeType(@PathVariable Long id) {
        log.debug("REST request to get MyAttributeType : {}", id);
        MyAttributeTypeDTO myAttributeTypeDTO = myAttributeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myAttributeTypeDTO));
    }

    /**
     * DELETE  /my-attribute-types/:id : delete the "id" myAttributeType.
     *
     * @param id the id of the myAttributeTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-attribute-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyAttributeType(@PathVariable Long id) {
        log.debug("REST request to delete MyAttributeType : {}", id);
        myAttributeTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
