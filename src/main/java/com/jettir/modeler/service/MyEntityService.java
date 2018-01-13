package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyEntityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyEntity.
 */
public interface MyEntityService {

    /**
     * Save a myEntity.
     *
     * @param myEntityDTO the entity to save
     * @return the persisted entity
     */
    MyEntityDTO save(MyEntityDTO myEntityDTO);

    /**
     *  Get all the myEntities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyEntityDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myEntity.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyEntityDTO findOne(Long id);

    /**
     *  Delete the "id" myEntity.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
