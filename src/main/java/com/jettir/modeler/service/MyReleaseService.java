package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyReleaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyRelease.
 */
public interface MyReleaseService {

    /**
     * Save a myRelease.
     *
     * @param myReleaseDTO the entity to save
     * @return the persisted entity
     */
    MyReleaseDTO save(MyReleaseDTO myReleaseDTO);

    /**
     *  Get all the myReleases.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyReleaseDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myRelease.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyReleaseDTO findOne(Long id);

    /**
     *  Delete the "id" myRelease.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
