package com.jettir.modeler.service;

import com.jettir.modeler.service.dto.MyFormViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MyFormView.
 */
public interface MyFormViewService {

    /**
     * Save a myFormView.
     *
     * @param myFormViewDTO the entity to save
     * @return the persisted entity
     */
    MyFormViewDTO save(MyFormViewDTO myFormViewDTO);

    /**
     *  Get all the myFormViews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MyFormViewDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" myFormView.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MyFormViewDTO findOne(Long id);

    /**
     *  Delete the "id" myFormView.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
