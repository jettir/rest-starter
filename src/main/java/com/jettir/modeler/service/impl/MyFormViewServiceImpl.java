package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyFormViewService;
import com.jettir.modeler.domain.MyFormView;
import com.jettir.modeler.repository.MyFormViewRepository;
import com.jettir.modeler.service.dto.MyFormViewDTO;
import com.jettir.modeler.service.mapper.MyFormViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyFormView.
 */
@Service
@Transactional
public class MyFormViewServiceImpl implements MyFormViewService{

    private final Logger log = LoggerFactory.getLogger(MyFormViewServiceImpl.class);

    private final MyFormViewRepository myFormViewRepository;

    private final MyFormViewMapper myFormViewMapper;

    public MyFormViewServiceImpl(MyFormViewRepository myFormViewRepository, MyFormViewMapper myFormViewMapper) {
        this.myFormViewRepository = myFormViewRepository;
        this.myFormViewMapper = myFormViewMapper;
    }

    /**
     * Save a myFormView.
     *
     * @param myFormViewDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyFormViewDTO save(MyFormViewDTO myFormViewDTO) {
        log.debug("Request to save MyFormView : {}", myFormViewDTO);
        MyFormView myFormView = myFormViewMapper.toEntity(myFormViewDTO);
        myFormView = myFormViewRepository.save(myFormView);
        return myFormViewMapper.toDto(myFormView);
    }

    /**
     *  Get all the myFormViews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyFormViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyFormViews");
        return myFormViewRepository.findAll(pageable)
            .map(myFormViewMapper::toDto);
    }

    /**
     *  Get one myFormView by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyFormViewDTO findOne(Long id) {
        log.debug("Request to get MyFormView : {}", id);
        MyFormView myFormView = myFormViewRepository.findOne(id);
        return myFormViewMapper.toDto(myFormView);
    }

    /**
     *  Delete the  myFormView by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyFormView : {}", id);
        myFormViewRepository.delete(id);
    }
}
