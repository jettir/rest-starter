package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyEntityTypeService;
import com.jettir.modeler.domain.MyEntityType;
import com.jettir.modeler.repository.MyEntityTypeRepository;
import com.jettir.modeler.service.dto.MyEntityTypeDTO;
import com.jettir.modeler.service.mapper.MyEntityTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyEntityType.
 */
@Service
@Transactional
public class MyEntityTypeServiceImpl implements MyEntityTypeService{

    private final Logger log = LoggerFactory.getLogger(MyEntityTypeServiceImpl.class);

    private final MyEntityTypeRepository myEntityTypeRepository;

    private final MyEntityTypeMapper myEntityTypeMapper;

    public MyEntityTypeServiceImpl(MyEntityTypeRepository myEntityTypeRepository, MyEntityTypeMapper myEntityTypeMapper) {
        this.myEntityTypeRepository = myEntityTypeRepository;
        this.myEntityTypeMapper = myEntityTypeMapper;
    }

    /**
     * Save a myEntityType.
     *
     * @param myEntityTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyEntityTypeDTO save(MyEntityTypeDTO myEntityTypeDTO) {
        log.debug("Request to save MyEntityType : {}", myEntityTypeDTO);
        MyEntityType myEntityType = myEntityTypeMapper.toEntity(myEntityTypeDTO);
        myEntityType = myEntityTypeRepository.save(myEntityType);
        return myEntityTypeMapper.toDto(myEntityType);
    }

    /**
     *  Get all the myEntityTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyEntityTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyEntityTypes");
        return myEntityTypeRepository.findAll(pageable)
            .map(myEntityTypeMapper::toDto);
    }

    /**
     *  Get one myEntityType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyEntityTypeDTO findOne(Long id) {
        log.debug("Request to get MyEntityType : {}", id);
        MyEntityType myEntityType = myEntityTypeRepository.findOne(id);
        return myEntityTypeMapper.toDto(myEntityType);
    }

    /**
     *  Delete the  myEntityType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyEntityType : {}", id);
        myEntityTypeRepository.delete(id);
    }
}
