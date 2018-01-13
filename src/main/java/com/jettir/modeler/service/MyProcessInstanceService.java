package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyProcessInstanceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyProcessInstance.
 */
public interface MyProcessInstanceService {

    /**
     * Save a myProcessInstance.
     *
     * @param myProcessInstanceDTO the entity to save
     * @return the persisted entity
     */
    MyProcessInstanceDTO save(MyProcessInstanceDTO myProcessInstanceDTO);

    /**
     *  Get all the myProcessInstances.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyProcessInstanceDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myProcessInstance.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyProcessInstanceDTO findOne(Long id);

    /**
     *  Delete the "id" myProcessInstance.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
