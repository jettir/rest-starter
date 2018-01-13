package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyBusinessRuleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyBusinessRule.
 */
public interface MyBusinessRuleService {

    /**
     * Save a myBusinessRule.
     *
     * @param myBusinessRuleDTO the entity to save
     * @return the persisted entity
     */
    MyBusinessRuleDTO save(MyBusinessRuleDTO myBusinessRuleDTO);

    /**
     *  Get all the myBusinessRules.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyBusinessRuleDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myBusinessRule.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyBusinessRuleDTO findOne(Long id);

    /**
     *  Delete the "id" myBusinessRule.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
