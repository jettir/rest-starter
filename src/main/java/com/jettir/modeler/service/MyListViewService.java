package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyListViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyListView.
 */
public interface MyListViewService {

    /**
     * Save a myListView.
     *
     * @param myListViewDTO the entity to save
     * @return the persisted entity
     */
    MyListViewDTO save(MyListViewDTO myListViewDTO);

    /**
     *  Get all the myListViews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyListViewDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myListView.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyListViewDTO findOne(Long id);

    /**
     *  Delete the "id" myListView.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
