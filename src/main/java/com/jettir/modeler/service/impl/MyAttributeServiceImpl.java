package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyAttributeService;
import com.jettir.modeler.domain.MyAttribute;
import com.jettir.modeler.repository.MyAttributeRepository;
import com.jettir.modeler.service.dto.MyAttributeDTO;
import com.jettir.modeler.service.mapper.MyAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyAttribute.
 */
@Service
@Transactional
public class MyAttributeServiceImpl implements MyAttributeService{

    private final Logger log = LoggerFactory.getLogger(MyAttributeServiceImpl.class);

    private final MyAttributeRepository myAttributeRepository;

    private final MyAttributeMapper myAttributeMapper;

    public MyAttributeServiceImpl(MyAttributeRepository myAttributeRepository, MyAttributeMapper myAttributeMapper) {
        this.myAttributeRepository = myAttributeRepository;
        this.myAttributeMapper = myAttributeMapper;
    }

    /**
     * Save a myAttribute.
     *
     * @param myAttributeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyAttributeDTO save(MyAttributeDTO myAttributeDTO) {
        log.debug("Request to save MyAttribute : {}", myAttributeDTO);
        MyAttribute myAttribute = myAttributeMapper.toEntity(myAttributeDTO);
        myAttribute = myAttributeRepository.save(myAttribute);
        return myAttributeMapper.toDto(myAttribute);
    }

    /**
     *  Get all the myAttributes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyAttributeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyAttributes");
        return myAttributeRepository.findAll(pageable)
            .map(myAttributeMapper::toDto);
    }

    /**
     *  Get one myAttribute by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyAttributeDTO findOne(Long id) {
        log.debug("Request to get MyAttribute : {}", id);
        MyAttribute myAttribute = myAttributeRepository.findOne(id);
        return myAttributeMapper.toDto(myAttribute);
    }

    /**
     *  Delete the  myAttribute by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyAttribute : {}", id);
        myAttributeRepository.delete(id);
    }
}
