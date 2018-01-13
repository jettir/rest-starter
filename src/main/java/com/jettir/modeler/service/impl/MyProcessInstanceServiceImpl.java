package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyProcessInstanceService;
import com.jettir.modeler.domain.MyProcessInstance;
import com.jettir.modeler.repository.MyProcessInstanceRepository;
import com.jettir.modeler.service.dto.MyProcessInstanceDTO;
import com.jettir.modeler.service.mapper.MyProcessInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyProcessInstance.
 */
@Service
@Transactional
public class MyProcessInstanceServiceImpl implements MyProcessInstanceService{

    private final Logger log = LoggerFactory.getLogger(MyProcessInstanceServiceImpl.class);

    private final MyProcessInstanceRepository myProcessInstanceRepository;

    private final MyProcessInstanceMapper myProcessInstanceMapper;

    public MyProcessInstanceServiceImpl(MyProcessInstanceRepository myProcessInstanceRepository, MyProcessInstanceMapper myProcessInstanceMapper) {
        this.myProcessInstanceRepository = myProcessInstanceRepository;
        this.myProcessInstanceMapper = myProcessInstanceMapper;
    }

    /**
     * Save a myProcessInstance.
     *
     * @param myProcessInstanceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyProcessInstanceDTO save(MyProcessInstanceDTO myProcessInstanceDTO) {
        log.debug("Request to save MyProcessInstance : {}", myProcessInstanceDTO);
        MyProcessInstance myProcessInstance = myProcessInstanceMapper.toEntity(myProcessInstanceDTO);
        myProcessInstance = myProcessInstanceRepository.save(myProcessInstance);
        return myProcessInstanceMapper.toDto(myProcessInstance);
    }

    /**
     *  Get all the myProcessInstances.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyProcessInstanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyProcessInstances");
        return myProcessInstanceRepository.findAll(pageable)
            .map(myProcessInstanceMapper::toDto);
    }

    /**
     *  Get one myProcessInstance by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyProcessInstanceDTO findOne(Long id) {
        log.debug("Request to get MyProcessInstance : {}", id);
        MyProcessInstance myProcessInstance = myProcessInstanceRepository.findOne(id);
        return myProcessInstanceMapper.toDto(myProcessInstance);
    }

    /**
     *  Delete the  myProcessInstance by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyProcessInstance : {}", id);
        myProcessInstanceRepository.delete(id);
    }
}
