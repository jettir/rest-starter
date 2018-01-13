package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyEntity and its DTO MyEntityDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class, MyEntityTypeMapper.class, MyBusinessRuleMapper.class})
public interface MyEntityMapper extends EntityMapper<MyEntityDTO, MyEntity> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    @Mapping(source = "myEntityType.id", target = "myEntityTypeId")
    MyEntityDTO toDto(MyEntity myEntity); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    @Mapping(source = "myEntityTypeId", target = "myEntityType")
    MyEntity toEntity(MyEntityDTO myEntityDTO);

    default MyEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyEntity myEntity = new MyEntity();
        myEntity.setId(id);
        return myEntity;
    }
}
