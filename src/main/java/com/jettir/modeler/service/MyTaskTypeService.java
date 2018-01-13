package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyTaskTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyTaskType.
 */
public interface MyTaskTypeService {

    /**
     * Save a myTaskType.
     *
     * @param myTaskTypeDTO the entity to save
     * @return the persisted entity
     */
    MyTaskTypeDTO save(MyTaskTypeDTO myTaskTypeDTO);

    /**
     *  Get all the myTaskTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyTaskTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myTaskType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyTaskTypeDTO findOne(Long id);

    /**
     *  Delete the "id" myTaskType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
