package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyEventTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyEventType and its DTO MyEventTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyEventTypeMapper extends EntityMapper<MyEventTypeDTO, MyEventType> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyEventTypeDTO toDto(MyEventType myEventType); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyEventType toEntity(MyEventTypeDTO myEventTypeDTO);

    default MyEventType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyEventType myEventType = new MyEventType();
        myEventType.setId(id);
        return myEventType;
    }
}
