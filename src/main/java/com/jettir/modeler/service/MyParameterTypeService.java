package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyParameterTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyParameterType.
 */
public interface MyParameterTypeService {

    /**
     * Save a myParameterType.
     *
     * @param myParameterTypeDTO the entity to save
     * @return the persisted entity
     */
    MyParameterTypeDTO save(MyParameterTypeDTO myParameterTypeDTO);

    /**
     *  Get all the myParameterTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyParameterTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myParameterType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyParameterTypeDTO findOne(Long id);

    /**
     *  Delete the "id" myParameterType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
