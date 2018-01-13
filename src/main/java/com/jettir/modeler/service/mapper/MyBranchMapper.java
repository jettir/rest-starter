package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyBranchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyBranch and its DTO MyBranchDTO.
 */
@Mapper(componentModel = "spring", uses = {MyReleaseMapper.class})
public interface MyBranchMapper extends EntityMapper<MyBranchDTO, MyBranch> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyBranchDTO toDto(MyBranch myBranch); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyBranch toEntity(MyBranchDTO myBranchDTO);

    default MyBranch fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyBranch myBranch = new MyBranch();
        myBranch.setId(id);
        return myBranch;
    }
}
