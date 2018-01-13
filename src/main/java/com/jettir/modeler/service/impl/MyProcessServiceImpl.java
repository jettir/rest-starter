package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyProcessService;
import com.jettir.modeler.domain.MyProcess;
import com.jettir.modeler.repository.MyProcessRepository;
import com.jettir.modeler.service.dto.MyProcessDTO;
import com.jettir.modeler.service.mapper.MyProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyProcess.
 */
@Service
@Transactional
public class MyProcessServiceImpl implements MyProcessService{

    private final Logger log = LoggerFactory.getLogger(MyProcessServiceImpl.class);

    private final MyProcessRepository myProcessRepository;

    private final MyProcessMapper myProcessMapper;

    public MyProcessServiceImpl(MyProcessRepository myProcessRepository, MyProcessMapper myProcessMapper) {
        this.myProcessRepository = myProcessRepository;
        this.myProcessMapper = myProcessMapper;
    }

    /**
     * Save a myProcess.
     *
     * @param myProcessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyProcessDTO save(MyProcessDTO myProcessDTO) {
        log.debug("Request to save MyProcess : {}", myProcessDTO);
        MyProcess myProcess = myProcessMapper.toEntity(myProcessDTO);
        myProcess = myProcessRepository.save(myProcess);
        return myProcessMapper.toDto(myProcess);
    }

    /**
     *  Get all the myProcesses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyProcesses");
        return myProcessRepository.findAll(pageable)
            .map(myProcessMapper::toDto);
    }

    /**
     *  Get one myProcess by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyProcessDTO findOne(Long id) {
        log.debug("Request to get MyProcess : {}", id);
        MyProcess myProcess = myProcessRepository.findOne(id);
        return myProcessMapper.toDto(myProcess);
    }

    /**
     *  Delete the  myProcess by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyProcess : {}", id);
        myProcessRepository.delete(id);
    }
}
