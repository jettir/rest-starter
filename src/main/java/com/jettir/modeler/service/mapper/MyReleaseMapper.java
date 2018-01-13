package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyReleaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyRelease and its DTO MyReleaseDTO.
 */
@Mapper(componentModel = "spring", uses = {MyBranchMapper.class})
public interface MyReleaseMapper extends EntityMapper<MyReleaseDTO, MyRelease> {

    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyReleaseDTO toDto(MyRelease myRelease); 

    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyRelease toEntity(MyReleaseDTO myReleaseDTO);

    default MyRelease fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyRelease myRelease = new MyRelease();
        myRelease.setId(id);
        return myRelease;
    }
}
