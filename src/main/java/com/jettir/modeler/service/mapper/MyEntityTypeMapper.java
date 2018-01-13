package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyEntityTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyEntityType and its DTO MyEntityTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyEntityTypeMapper extends EntityMapper<MyEntityTypeDTO, MyEntityType> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyEntityTypeDTO toDto(MyEntityType myEntityType); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyEntityType toEntity(MyEntityTypeDTO myEntityTypeDTO);

    default MyEntityType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyEntityType myEntityType = new MyEntityType();
        myEntityType.setId(id);
        return myEntityType;
    }
}
