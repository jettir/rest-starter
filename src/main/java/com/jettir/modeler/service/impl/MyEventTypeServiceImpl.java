package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyEventTypeService;
import com.jettir.modeler.domain.MyEventType;
import com.jettir.modeler.repository.MyEventTypeRepository;
import com.jettir.modeler.service.dto.MyEventTypeDTO;
import com.jettir.modeler.service.mapper.MyEventTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyEventType.
 */
@Service
@Transactional
public class MyEventTypeServiceImpl implements MyEventTypeService{

    private final Logger log = LoggerFactory.getLogger(MyEventTypeServiceImpl.class);

    private final MyEventTypeRepository myEventTypeRepository;

    private final MyEventTypeMapper myEventTypeMapper;

    public MyEventTypeServiceImpl(MyEventTypeRepository myEventTypeRepository, MyEventTypeMapper myEventTypeMapper) {
        this.myEventTypeRepository = myEventTypeRepository;
        this.myEventTypeMapper = myEventTypeMapper;
    }

    /**
     * Save a myEventType.
     *
     * @param myEventTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyEventTypeDTO save(MyEventTypeDTO myEventTypeDTO) {
        log.debug("Request to save MyEventType : {}", myEventTypeDTO);
        MyEventType myEventType = myEventTypeMapper.toEntity(myEventTypeDTO);
        myEventType = myEventTypeRepository.save(myEventType);
        return myEventTypeMapper.toDto(myEventType);
    }

    /**
     *  Get all the myEventTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyEventTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyEventTypes");
        return myEventTypeRepository.findAll(pageable)
            .map(myEventTypeMapper::toDto);
    }

    /**
     *  Get one myEventType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyEventTypeDTO findOne(Long id) {
        log.debug("Request to get MyEventType : {}", id);
        MyEventType myEventType = myEventTypeRepository.findOne(id);
        return myEventTypeMapper.toDto(myEventType);
    }

    /**
     *  Delete the  myEventType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyEventType : {}", id);
        myEventTypeRepository.delete(id);
    }
}
