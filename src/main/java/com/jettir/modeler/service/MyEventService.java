package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyEventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyEvent.
 */
public interface MyEventService {

    /**
     * Save a myEvent.
     *
     * @param myEventDTO the entity to save
     * @return the persisted entity
     */
    MyEventDTO save(MyEventDTO myEventDTO);

    /**
     *  Get all the myEvents.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyEventDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myEvent.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyEventDTO findOne(Long id);

    /**
     *  Delete the "id" myEvent.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
