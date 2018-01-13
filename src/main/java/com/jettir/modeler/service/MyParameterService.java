package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyParameterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyParameter.
 */
public interface MyParameterService {

    /**
     * Save a myParameter.
     *
     * @param myParameterDTO the entity to save
     * @return the persisted entity
     */
    MyParameterDTO save(MyParameterDTO myParameterDTO);

    /**
     *  Get all the myParameters.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyParameterDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myParameter.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyParameterDTO findOne(Long id);

    /**
     *  Delete the "id" myParameter.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
