package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyProcess and its DTO MyProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class, MyReleaseMapper.class})
public interface MyProcessMapper extends EntityMapper<MyProcessDTO, MyProcess> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyProcessDTO toDto(MyProcess myProcess); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyProcess toEntity(MyProcessDTO myProcessDTO);

    default MyProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyProcess myProcess = new MyProcess();
        myProcess.setId(id);
        return myProcess;
    }
}
