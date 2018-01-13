package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyEntityTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyEntityType.
 */
public interface MyEntityTypeService {

    /**
     * Save a myEntityType.
     *
     * @param myEntityTypeDTO the entity to save
     * @return the persisted entity
     */
    MyEntityTypeDTO save(MyEntityTypeDTO myEntityTypeDTO);

    /**
     *  Get all the myEntityTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyEntityTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myEntityType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyEntityTypeDTO findOne(Long id);

    /**
     *  Delete the "id" myEntityType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
