package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyTaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyTask.
 */
public interface MyTaskService {

    /**
     * Save a myTask.
     *
     * @param myTaskDTO the entity to save
     * @return the persisted entity
     */
    MyTaskDTO save(MyTaskDTO myTaskDTO);

    /**
     *  Get all the myTasks.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyTaskDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myTask.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyTaskDTO findOne(Long id);

    /**
     *  Delete the "id" myTask.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
