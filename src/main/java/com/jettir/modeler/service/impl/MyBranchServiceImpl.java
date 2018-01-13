package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyBranchService;
import com.jettir.modeler.domain.MyBranch;
import com.jettir.modeler.repository.MyBranchRepository;
import com.jettir.modeler.service.dto.MyBranchDTO;
import com.jettir.modeler.service.mapper.MyBranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyBranch.
 */
@Service
@Transactional
public class MyBranchServiceImpl implements MyBranchService{

    private final Logger log = LoggerFactory.getLogger(MyBranchServiceImpl.class);

    private final MyBranchRepository myBranchRepository;

    private final MyBranchMapper myBranchMapper;

    public MyBranchServiceImpl(MyBranchRepository myBranchRepository, MyBranchMapper myBranchMapper) {
        this.myBranchRepository = myBranchRepository;
        this.myBranchMapper = myBranchMapper;
    }

    /**
     * Save a myBranch.
     *
     * @param myBranchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyBranchDTO save(MyBranchDTO myBranchDTO) {
        log.debug("Request to save MyBranch : {}", myBranchDTO);
        MyBranch myBranch = myBranchMapper.toEntity(myBranchDTO);
        myBranch = myBranchRepository.save(myBranch);
        return myBranchMapper.toDto(myBranch);
    }

    /**
     *  Get all the myBranches.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyBranchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyBranches");
        return myBranchRepository.findAll(pageable)
            .map(myBranchMapper::toDto);
    }

    /**
     *  Get one myBranch by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyBranchDTO findOne(Long id) {
        log.debug("Request to get MyBranch : {}", id);
        MyBranch myBranch = myBranchRepository.findOne(id);
        return myBranchMapper.toDto(myBranch);
    }

    /**
     *  Delete the  myBranch by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyBranch : {}", id);
        myBranchRepository.delete(id);
    }
}
