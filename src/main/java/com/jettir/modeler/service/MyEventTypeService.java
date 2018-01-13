package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyEventTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyEventType.
 */
public interface MyEventTypeService {

    /**
     * Save a myEventType.
     *
     * @param myEventTypeDTO the entity to save
     * @return the persisted entity
     */
    MyEventTypeDTO save(MyEventTypeDTO myEventTypeDTO);

    /**
     *  Get all the myEventTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyEventTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myEventType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyEventTypeDTO findOne(Long id);

    /**
     *  Delete the "id" myEventType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
