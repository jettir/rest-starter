package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyParameterTypeService;
import com.jettir.modeler.domain.MyParameterType;
import com.jettir.modeler.repository.MyParameterTypeRepository;
import com.jettir.modeler.service.dto.MyParameterTypeDTO;
import com.jettir.modeler.service.mapper.MyParameterTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyParameterType.
 */
@Service
@Transactional
public class MyParameterTypeServiceImpl implements MyParameterTypeService{

    private final Logger log = LoggerFactory.getLogger(MyParameterTypeServiceImpl.class);

    private final MyParameterTypeRepository myParameterTypeRepository;

    private final MyParameterTypeMapper myParameterTypeMapper;

    public MyParameterTypeServiceImpl(MyParameterTypeRepository myParameterTypeRepository, MyParameterTypeMapper myParameterTypeMapper) {
        this.myParameterTypeRepository = myParameterTypeRepository;
        this.myParameterTypeMapper = myParameterTypeMapper;
    }

    /**
     * Save a myParameterType.
     *
     * @param myParameterTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyParameterTypeDTO save(MyParameterTypeDTO myParameterTypeDTO) {
        log.debug("Request to save MyParameterType : {}", myParameterTypeDTO);
        MyParameterType myParameterType = myParameterTypeMapper.toEntity(myParameterTypeDTO);
        myParameterType = myParameterTypeRepository.save(myParameterType);
        return myParameterTypeMapper.toDto(myParameterType);
    }

    /**
     *  Get all the myParameterTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyParameterTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyParameterTypes");
        return myParameterTypeRepository.findAll(pageable)
            .map(myParameterTypeMapper::toDto);
    }

    /**
     *  Get one myParameterType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyParameterTypeDTO findOne(Long id) {
        log.debug("Request to get MyParameterType : {}", id);
        MyParameterType myParameterType = myParameterTypeRepository.findOne(id);
        return myParameterTypeMapper.toDto(myParameterType);
    }

    /**
     *  Delete the  myParameterType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyParameterType : {}", id);
        myParameterTypeRepository.delete(id);
    }
}
