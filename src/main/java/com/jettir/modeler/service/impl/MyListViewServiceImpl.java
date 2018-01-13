package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyListViewService;
import com.jettir.modeler.domain.MyListView;
import com.jettir.modeler.repository.MyListViewRepository;
import com.jettir.modeler.service.dto.MyListViewDTO;
import com.jettir.modeler.service.mapper.MyListViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyListView.
 */
@Service
@Transactional
public class MyListViewServiceImpl implements MyListViewService{

    private final Logger log = LoggerFactory.getLogger(MyListViewServiceImpl.class);

    private final MyListViewRepository myListViewRepository;

    private final MyListViewMapper myListViewMapper;

    public MyListViewServiceImpl(MyListViewRepository myListViewRepository, MyListViewMapper myListViewMapper) {
        this.myListViewRepository = myListViewRepository;
        this.myListViewMapper = myListViewMapper;
    }

    /**
     * Save a myListView.
     *
     * @param myListViewDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyListViewDTO save(MyListViewDTO myListViewDTO) {
        log.debug("Request to save MyListView : {}", myListViewDTO);
        MyListView myListView = myListViewMapper.toEntity(myListViewDTO);
        myListView = myListViewRepository.save(myListView);
        return myListViewMapper.toDto(myListView);
    }

    /**
     *  Get all the myListViews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyListViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyListViews");
        return myListViewRepository.findAll(pageable)
            .map(myListViewMapper::toDto);
    }

    /**
     *  Get one myListView by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyListViewDTO findOne(Long id) {
        log.debug("Request to get MyListView : {}", id);
        MyListView myListView = myListViewRepository.findOne(id);
        return myListViewMapper.toDto(myListView);
    }

    /**
     *  Delete the  myListView by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyListView : {}", id);
        myListViewRepository.delete(id);
    }
}
