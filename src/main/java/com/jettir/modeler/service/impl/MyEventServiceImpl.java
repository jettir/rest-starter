package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyEventService;
import com.jettir.modeler.domain.MyEvent;
import com.jettir.modeler.repository.MyEventRepository;
import com.jettir.modeler.service.dto.MyEventDTO;
import com.jettir.modeler.service.mapper.MyEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyEvent.
 */
@Service
@Transactional
public class MyEventServiceImpl implements MyEventService{

    private final Logger log = LoggerFactory.getLogger(MyEventServiceImpl.class);

    private final MyEventRepository myEventRepository;

    private final MyEventMapper myEventMapper;

    public MyEventServiceImpl(MyEventRepository myEventRepository, MyEventMapper myEventMapper) {
        this.myEventRepository = myEventRepository;
        this.myEventMapper = myEventMapper;
    }

    /**
     * Save a myEvent.
     *
     * @param myEventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyEventDTO save(MyEventDTO myEventDTO) {
        log.debug("Request to save MyEvent : {}", myEventDTO);
        MyEvent myEvent = myEventMapper.toEntity(myEventDTO);
        myEvent = myEventRepository.save(myEvent);
        return myEventMapper.toDto(myEvent);
    }

    /**
     *  Get all the myEvents.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyEvents");
        return myEventRepository.findAll(pageable)
            .map(myEventMapper::toDto);
    }

    /**
     *  Get one myEvent by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyEventDTO findOne(Long id) {
        log.debug("Request to get MyEvent : {}", id);
        MyEvent myEvent = myEventRepository.findOne(id);
        return myEventMapper.toDto(myEvent);
    }

    /**
     *  Delete the  myEvent by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyEvent : {}", id);
        myEventRepository.delete(id);
    }
}
