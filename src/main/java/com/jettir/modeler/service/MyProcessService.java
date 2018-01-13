package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyProcessDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyProcess.
 */
public interface MyProcessService {

    /**
     * Save a myProcess.
     *
     * @param myProcessDTO the entity to save
     * @return the persisted entity
     */
    MyProcessDTO save(MyProcessDTO myProcessDTO);

    /**
     *  Get all the myProcesses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyProcessDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myProcess.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyProcessDTO findOne(Long id);

    /**
     *  Delete the "id" myProcess.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
