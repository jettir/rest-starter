package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyAttributeTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyAttributeType.
 */
public interface MyAttributeTypeService {

    /**
     * Save a myAttributeType.
     *
     * @param myAttributeTypeDTO the entity to save
     * @return the persisted entity
     */
    MyAttributeTypeDTO save(MyAttributeTypeDTO myAttributeTypeDTO);

    /**
     *  Get all the myAttributeTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyAttributeTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myAttributeType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyAttributeTypeDTO findOne(Long id);

    /**
     *  Delete the "id" myAttributeType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
