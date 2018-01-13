package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyEvent and its DTO MyEventDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyEventMapper extends EntityMapper<MyEventDTO, MyEvent> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyEventDTO toDto(MyEvent myEvent); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyEvent toEntity(MyEventDTO myEventDTO);

    default MyEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyEvent myEvent = new MyEvent();
        myEvent.setId(id);
        return myEvent;
    }
}
