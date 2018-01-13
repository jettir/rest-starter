package com.jettir.modeler.service.impl;

import com.jettir.modeler.service.MyBusinessRuleService;
import com.jettir.modeler.domain.MyBusinessRule;
import com.jettir.modeler.repository.MyBusinessRuleRepository;
import com.jettir.modeler.service.dto.MyBusinessRuleDTO;
import com.jettir.modeler.service.mapper.MyBusinessRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MyBusinessRule.
 */
@Service
@Transactional
public class MyBusinessRuleServiceImpl implements MyBusinessRuleService{

    private final Logger log = LoggerFactory.getLogger(MyBusinessRuleServiceImpl.class);

    private final MyBusinessRuleRepository myBusinessRuleRepository;

    private final MyBusinessRuleMapper myBusinessRuleMapper;

    public MyBusinessRuleServiceImpl(MyBusinessRuleRepository myBusinessRuleRepository, MyBusinessRuleMapper myBusinessRuleMapper) {
        this.myBusinessRuleRepository = myBusinessRuleRepository;
        this.myBusinessRuleMapper = myBusinessRuleMapper;
    }

    /**
     * Save a myBusinessRule.
     *
     * @param myBusinessRuleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyBusinessRuleDTO save(MyBusinessRuleDTO myBusinessRuleDTO) {
        log.debug("Request to save MyBusinessRule : {}", myBusinessRuleDTO);
        MyBusinessRule myBusinessRule = myBusinessRuleMapper.toEntity(myBusinessRuleDTO);
        myBusinessRule = myBusinessRuleRepository.save(myBusinessRule);
        return myBusinessRuleMapper.toDto(myBusinessRule);
    }

    /**
     *  Get all the myBusinessRules.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MyBusinessRuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyBusinessRules");
        return myBusinessRuleRepository.findAll(pageable)
            .map(myBusinessRuleMapper::toDto);
    }

    /**
     *  Get one myBusinessRule by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyBusinessRuleDTO findOne(Long id) {
        log.debug("Request to get MyBusinessRule : {}", id);
        MyBusinessRule myBusinessRule = myBusinessRuleRepository.findOne(id);
        return myBusinessRuleMapper.toDto(myBusinessRule);
    }

    /**
     *  Delete the  myBusinessRule by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyBusinessRule : {}", id);
        myBusinessRuleRepository.delete(id);
    }
}
