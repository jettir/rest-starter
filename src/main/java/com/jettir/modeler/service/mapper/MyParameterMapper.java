package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyParameterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyParameter and its DTO MyParameterDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class, MyParameterTypeMapper.class})
public interface MyParameterMapper extends EntityMapper<MyParameterDTO, MyParameter> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    @Mapping(source = "myParameterType.id", target = "myParameterTypeId")
    MyParameterDTO toDto(MyParameter myParameter); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    @Mapping(source = "myParameterTypeId", target = "myParameterType")
    MyParameter toEntity(MyParameterDTO myParameterDTO);

    default MyParameter fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyParameter myParameter = new MyParameter();
        myParameter.setId(id);
        return myParameter;
    }
}
