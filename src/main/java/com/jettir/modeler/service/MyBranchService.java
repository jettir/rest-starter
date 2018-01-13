package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyBranchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyBranch.
 */
public interface MyBranchService {

    /**
     * Save a myBranch.
     *
     * @param myBranchDTO the entity to save
     * @return the persisted entity
     */
    MyBranchDTO save(MyBranchDTO myBranchDTO);

    /**
     *  Get all the myBranches.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyBranchDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myBranch.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyBranchDTO findOne(Long id);

    /**
     *  Delete the "id" myBranch.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
