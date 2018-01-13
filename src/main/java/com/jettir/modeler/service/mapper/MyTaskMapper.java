package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyTask and its DTO MyTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyTaskMapper extends EntityMapper<MyTaskDTO, MyTask> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyTaskDTO toDto(MyTask myTask); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyTask toEntity(MyTaskDTO myTaskDTO);

    default MyTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyTask myTask = new MyTask();
        myTask.setId(id);
        return myTask;
    }
}
