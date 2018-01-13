package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyAttributeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyAttribute.
 */
public interface MyAttributeService {

    /**
     * Save a myAttribute.
     *
     * @param myAttributeDTO the entity to save
     * @return the persisted entity
     */
    MyAttributeDTO save(MyAttributeDTO myAttributeDTO);

    /**
     *  Get all the myAttributes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyAttributeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myAttribute.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyAttributeDTO findOne(Long id);

    /**
     *  Delete the "id" myAttribute.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
