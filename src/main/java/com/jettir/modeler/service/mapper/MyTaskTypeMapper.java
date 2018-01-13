package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyTaskTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyTaskType and its DTO MyTaskTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyTaskTypeMapper extends EntityMapper<MyTaskTypeDTO, MyTaskType> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyTaskTypeDTO toDto(MyTaskType myTaskType); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyTaskType toEntity(MyTaskTypeDTO myTaskTypeDTO);

    default MyTaskType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyTaskType myTaskType = new MyTaskType();
        myTaskType.setId(id);
        return myTaskType;
    }
}
