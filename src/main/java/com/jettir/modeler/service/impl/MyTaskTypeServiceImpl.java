package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyTaskTypeService;
import com.jettir.modeler.domain.MyTaskType;
import com.jettir.modeler.repository.MyTaskTypeRepository;
import com.jettir.modeler.service.dto.MyTaskTypeDTO;
import com.jettir.modeler.service.mapper.MyTaskTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyTaskType.
 */
@Service
@Transactional
public class MyTaskTypeServiceImpl implements MyTaskTypeService{

    private final Logger log = LoggerFactory.getLogger(MyTaskTypeServiceImpl.class);

    private final MyTaskTypeRepository myTaskTypeRepository;

    private final MyTaskTypeMapper myTaskTypeMapper;

    public MyTaskTypeServiceImpl(MyTaskTypeRepository myTaskTypeRepository, MyTaskTypeMapper myTaskTypeMapper) {
        this.myTaskTypeRepository = myTaskTypeRepository;
        this.myTaskTypeMapper = myTaskTypeMapper;
    }

    /**
     * Save a myTaskType.
     *
     * @param myTaskTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyTaskTypeDTO save(MyTaskTypeDTO myTaskTypeDTO) {
        log.debug("Request to save MyTaskType : {}", myTaskTypeDTO);
        MyTaskType myTaskType = myTaskTypeMapper.toEntity(myTaskTypeDTO);
        myTaskType = myTaskTypeRepository.save(myTaskType);
        return myTaskTypeMapper.toDto(myTaskType);
    }

    /**
     *  Get all the myTaskTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyTaskTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyTaskTypes");
        return myTaskTypeRepository.findAll(pageable)
            .map(myTaskTypeMapper::toDto);
    }

    /**
     *  Get one myTaskType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyTaskTypeDTO findOne(Long id) {
        log.debug("Request to get MyTaskType : {}", id);
        MyTaskType myTaskType = myTaskTypeRepository.findOne(id);
        return myTaskTypeMapper.toDto(myTaskType);
    }

    /**
     *  Delete the  myTaskType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyTaskType : {}", id);
        myTaskTypeRepository.delete(id);
    }
}
