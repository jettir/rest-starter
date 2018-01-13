package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyProcessInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyProcessInstance and its DTO MyProcessInstanceDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyProcessInstanceMapper extends EntityMapper<MyProcessInstanceDTO, MyProcessInstance> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyProcessInstanceDTO toDto(MyProcessInstance myProcessInstance); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyProcessInstance toEntity(MyProcessInstanceDTO myProcessInstanceDTO);

    default MyProcessInstance fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyProcessInstance myProcessInstance = new MyProcessInstance();
        myProcessInstance.setId(id);
        return myProcessInstance;
    }
}
