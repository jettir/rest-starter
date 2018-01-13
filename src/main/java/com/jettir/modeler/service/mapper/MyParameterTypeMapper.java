package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyParameterTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyParameterType and its DTO MyParameterTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyParameterTypeMapper extends EntityMapper<MyParameterTypeDTO, MyParameterType> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyParameterTypeDTO toDto(MyParameterType myParameterType); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyParameterType toEntity(MyParameterTypeDTO myParameterTypeDTO);

    default MyParameterType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyParameterType myParameterType = new MyParameterType();
        myParameterType.setId(id);
        return myParameterType;
    }
}
