package com.jettir.modeler.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jettir.modeler.service.MyBusinessRuleService;
import com.jettir.modeler.web.rest.errors.BadRequestAlertException;
import com.jettir.modeler.web.rest.util.HeaderUtil;
import com.jettir.modeler.web.rest.util.PaginationUtil;
import com.jettir.modeler.service.dto.MyBusinessRuleDTO;
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
 * REST controller for managing MyBusinessRule.
 */
@RestController
@RequestMapping("/api")
public class MyBusinessRuleResource {

    private final Logger log = LoggerFactory.getLogger(MyBusinessRuleResource.class);

    private static final String ENTITY_NAME = "myBusinessRule";

    private final MyBusinessRuleService myBusinessRuleService;

    public MyBusinessRuleResource(MyBusinessRuleService myBusinessRuleService) {
        this.myBusinessRuleService = myBusinessRuleService;
    }

    /**
     * POST  /my-business-rules : Create a new myBusinessRule.
     *
     * @param myBusinessRuleDTO the myBusinessRuleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myBusinessRuleDTO, or with status 400 (Bad Request) if the myBusinessRule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-business-rules")
    @Timed
    public ResponseEntity<MyBusinessRuleDTO> createMyBusinessRule(@RequestBody MyBusinessRuleDTO myBusinessRuleDTO) throws URISyntaxException {
        log.debug("REST request to save MyBusinessRule : {}", myBusinessRuleDTO);
        if (myBusinessRuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new myBusinessRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyBusinessRuleDTO result = myBusinessRuleService.save(myBusinessRuleDTO);
        return ResponseEntity.created(new URI("/api/my-business-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-business-rules : Updates an existing myBusinessRule.
     *
     * @param myBusinessRuleDTO the myBusinessRuleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myBusinessRuleDTO,
     * or with status 400 (Bad Request) if the myBusinessRuleDTO is not valid,
     * or with status 500 (Internal Server Error) if the myBusinessRuleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-business-rules")
    @Timed
    public ResponseEntity<MyBusinessRuleDTO> updateMyBusinessRule(@RequestBody MyBusinessRuleDTO myBusinessRuleDTO) throws URISyntaxException {
        log.debug("REST request to update MyBusinessRule : {}", myBusinessRuleDTO);
        if (myBusinessRuleDTO.getId() == null) {
            return createMyBusinessRule(myBusinessRuleDTO);
        }
        MyBusinessRuleDTO result = myBusinessRuleService.save(myBusinessRuleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myBusinessRuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-business-rules : get all the myBusinessRules.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of myBusinessRules in body
     */
    @GetMapping("/my-business-rules")
    @Timed
    public ResponseEntity<List<MyBusinessRuleDTO>> getAllMyBusinessRules(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of MyBusinessRules");
        Page<MyBusinessRuleDTO> page = myBusinessRuleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/my-business-rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /my-business-rules/:id : get the "id" myBusinessRule.
     *
     * @param id the id of the myBusinessRuleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myBusinessRuleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-business-rules/{id}")
    @Timed
    public ResponseEntity<MyBusinessRuleDTO> getMyBusinessRule(@PathVariable Long id) {
        log.debug("REST request to get MyBusinessRule : {}", id);
        MyBusinessRuleDTO myBusinessRuleDTO = myBusinessRuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myBusinessRuleDTO));
    }

    /**
     * DELETE  /my-business-rules/:id : delete the "id" myBusinessRule.
     *
     * @param id the id of the myBusinessRuleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-business-rules/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyBusinessRule(@PathVariable Long id) {
        log.debug("REST request to delete MyBusinessRule : {}", id);
        myBusinessRuleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
