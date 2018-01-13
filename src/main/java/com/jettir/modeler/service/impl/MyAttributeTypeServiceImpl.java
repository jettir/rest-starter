package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyAttributeTypeService;
import com.jettir.modeler.domain.MyAttributeType;
import com.jettir.modeler.repository.MyAttributeTypeRepository;
import com.jettir.modeler.service.dto.MyAttributeTypeDTO;
import com.jettir.modeler.service.mapper.MyAttributeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyAttributeType.
 */
@Service
@Transactional
public class MyAttributeTypeServiceImpl implements MyAttributeTypeService{

    private final Logger log = LoggerFactory.getLogger(MyAttributeTypeServiceImpl.class);

    private final MyAttributeTypeRepository myAttributeTypeRepository;

    private final MyAttributeTypeMapper myAttributeTypeMapper;

    public MyAttributeTypeServiceImpl(MyAttributeTypeRepository myAttributeTypeRepository, MyAttributeTypeMapper myAttributeTypeMapper) {
        this.myAttributeTypeRepository = myAttributeTypeRepository;
        this.myAttributeTypeMapper = myAttributeTypeMapper;
    }

    /**
     * Save a myAttributeType.
     *
     * @param myAttributeTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyAttributeTypeDTO save(MyAttributeTypeDTO myAttributeTypeDTO) {
        log.debug("Request to save MyAttributeType : {}", myAttributeTypeDTO);
        MyAttributeType myAttributeType = myAttributeTypeMapper.toEntity(myAttributeTypeDTO);
        myAttributeType = myAttributeTypeRepository.save(myAttributeType);
        return myAttributeTypeMapper.toDto(myAttributeType);
    }

    /**
     *  Get all the myAttributeTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyAttributeTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyAttributeTypes");
        return myAttributeTypeRepository.findAll(pageable)
            .map(myAttributeTypeMapper::toDto);
    }

    /**
     *  Get one myAttributeType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyAttributeTypeDTO findOne(Long id) {
        log.debug("Request to get MyAttributeType : {}", id);
        MyAttributeType myAttributeType = myAttributeTypeRepository.findOne(id);
        return myAttributeTypeMapper.toDto(myAttributeType);
    }

    /**
     *  Delete the  myAttributeType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyAttributeType : {}", id);
        myAttributeTypeRepository.delete(id);
    }
}
