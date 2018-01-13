package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyBusinessRuleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyBusinessRule and its DTO MyBusinessRuleDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyBusinessRuleMapper extends EntityMapper<MyBusinessRuleDTO, MyBusinessRule> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyBusinessRuleDTO toDto(MyBusinessRule myBusinessRule); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    @Mapping(target = "myEntities", ignore = true)
    MyBusinessRule toEntity(MyBusinessRuleDTO myBusinessRuleDTO);

    default MyBusinessRule fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyBusinessRule myBusinessRule = new MyBusinessRule();
        myBusinessRule.setId(id);
        return myBusinessRule;
    }
}
