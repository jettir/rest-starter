package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyTaskService;
import com.jettir.modeler.domain.MyTask;
import com.jettir.modeler.repository.MyTaskRepository;
import com.jettir.modeler.service.dto.MyTaskDTO;
import com.jettir.modeler.service.mapper.MyTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyTask.
 */
@Service
@Transactional
public class MyTaskServiceImpl implements MyTaskService{

    private final Logger log = LoggerFactory.getLogger(MyTaskServiceImpl.class);

    private final MyTaskRepository myTaskRepository;

    private final MyTaskMapper myTaskMapper;

    public MyTaskServiceImpl(MyTaskRepository myTaskRepository, MyTaskMapper myTaskMapper) {
        this.myTaskRepository = myTaskRepository;
        this.myTaskMapper = myTaskMapper;
    }

    /**
     * Save a myTask.
     *
     * @param myTaskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyTaskDTO save(MyTaskDTO myTaskDTO) {
        log.debug("Request to save MyTask : {}", myTaskDTO);
        MyTask myTask = myTaskMapper.toEntity(myTaskDTO);
        myTask = myTaskRepository.save(myTask);
        return myTaskMapper.toDto(myTask);
    }

    /**
     *  Get all the myTasks.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyTaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyTasks");
        return myTaskRepository.findAll(pageable)
            .map(myTaskMapper::toDto);
    }

    /**
     *  Get one myTask by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyTaskDTO findOne(Long id) {
        log.debug("Request to get MyTask : {}", id);
        MyTask myTask = myTaskRepository.findOne(id);
        return myTaskMapper.toDto(myTask);
    }

    /**
     *  Delete the  myTask by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyTask : {}", id);
        myTaskRepository.delete(id);
    }
}
