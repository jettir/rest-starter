package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyReleaseService;
import com.jettir.modeler.domain.MyRelease;
import com.jettir.modeler.repository.MyReleaseRepository;
import com.jettir.modeler.service.dto.MyReleaseDTO;
import com.jettir.modeler.service.mapper.MyReleaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyRelease.
 */
@Service
@Transactional
public class MyReleaseServiceImpl implements MyReleaseService{

    private final Logger log = LoggerFactory.getLogger(MyReleaseServiceImpl.class);

    private final MyReleaseRepository myReleaseRepository;

    private final MyReleaseMapper myReleaseMapper;

    public MyReleaseServiceImpl(MyReleaseRepository myReleaseRepository, MyReleaseMapper myReleaseMapper) {
        this.myReleaseRepository = myReleaseRepository;
        this.myReleaseMapper = myReleaseMapper;
    }

    /**
     * Save a myRelease.
     *
     * @param myReleaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyReleaseDTO save(MyReleaseDTO myReleaseDTO) {
        log.debug("Request to save MyRelease : {}", myReleaseDTO);
        MyRelease myRelease = myReleaseMapper.toEntity(myReleaseDTO);
        myRelease = myReleaseRepository.save(myRelease);
        return myReleaseMapper.toDto(myRelease);
    }

    /**
     *  Get all the myReleases.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyReleaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyReleases");
        return myReleaseRepository.findAll(pageable)
            .map(myReleaseMapper::toDto);
    }

    /**
     *  Get one myRelease by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyReleaseDTO findOne(Long id) {
        log.debug("Request to get MyRelease : {}", id);
        MyRelease myRelease = myReleaseRepository.findOne(id);
        return myReleaseMapper.toDto(myRelease);
    }

    /**
     *  Delete the  myRelease by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyRelease : {}", id);
        myReleaseRepository.delete(id);
    }
}
