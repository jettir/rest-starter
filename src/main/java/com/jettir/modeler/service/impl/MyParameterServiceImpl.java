package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyParameterService;
import com.jettir.modeler.domain.MyParameter;
import com.jettir.modeler.repository.MyParameterRepository;
import com.jettir.modeler.service.dto.MyParameterDTO;
import com.jettir.modeler.service.mapper.MyParameterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyParameter.
 */
@Service
@Transactional
public class MyParameterServiceImpl implements MyParameterService{

    private final Logger log = LoggerFactory.getLogger(MyParameterServiceImpl.class);

    private final MyParameterRepository myParameterRepository;

    private final MyParameterMapper myParameterMapper;

    public MyParameterServiceImpl(MyParameterRepository myParameterRepository, MyParameterMapper myParameterMapper) {
        this.myParameterRepository = myParameterRepository;
        this.myParameterMapper = myParameterMapper;
    }

    /**
     * Save a myParameter.
     *
     * @param myParameterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyParameterDTO save(MyParameterDTO myParameterDTO) {
        log.debug("Request to save MyParameter : {}", myParameterDTO);
        MyParameter myParameter = myParameterMapper.toEntity(myParameterDTO);
        myParameter = myParameterRepository.save(myParameter);
        return myParameterMapper.toDto(myParameter);
    }

    /**
     *  Get all the myParameters.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyParameterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyParameters");
        return myParameterRepository.findAll(pageable)
            .map(myParameterMapper::toDto);
    }

    /**
     *  Get one myParameter by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyParameterDTO findOne(Long id) {
        log.debug("Request to get MyParameter : {}", id);
        MyParameter myParameter = myParameterRepository.findOne(id);
        return myParameterMapper.toDto(myParameter);
    }

    /**
     *  Delete the  myParameter by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyParameter : {}", id);
        myParameterRepository.delete(id);
    }
}
