package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyEntityService;
import com.jettir.modeler.domain.MyEntity;
import com.jettir.modeler.repository.MyEntityRepository;
import com.jettir.modeler.service.dto.MyEntityDTO;
import com.jettir.modeler.service.mapper.MyEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyEntity.
 */
@Service
@Transactional
public class MyEntityServiceImpl implements MyEntityService{

    private final Logger log = LoggerFactory.getLogger(MyEntityServiceImpl.class);

    private final MyEntityRepository myEntityRepository;

    private final MyEntityMapper myEntityMapper;

    public MyEntityServiceImpl(MyEntityRepository myEntityRepository, MyEntityMapper myEntityMapper) {
        this.myEntityRepository = myEntityRepository;
        this.myEntityMapper = myEntityMapper;
    }

    /**
     * Save a myEntity.
     *
     * @param myEntityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyEntityDTO save(MyEntityDTO myEntityDTO) {
        log.debug("Request to save MyEntity : {}", myEntityDTO);
        MyEntity myEntity = myEntityMapper.toEntity(myEntityDTO);
        myEntity = myEntityRepository.save(myEntity);
        return myEntityMapper.toDto(myEntity);
    }

    /**
     *  Get all the myEntities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyEntityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyEntities");
        return myEntityRepository.findAll(pageable)
            .map(myEntityMapper::toDto);
    }

    /**
     *  Get one myEntity by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyEntityDTO findOne(Long id) {
        log.debug("Request to get MyEntity : {}", id);
        MyEntity myEntity = myEntityRepository.findOneWithEagerRelationships(id);
        return myEntityMapper.toDto(myEntity);
    }

    /**
     *  Delete the  myEntity by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyEntity : {}", id);
        myEntityRepository.delete(id);
    }
}
