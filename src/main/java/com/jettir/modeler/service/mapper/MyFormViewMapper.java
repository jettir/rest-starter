package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyFormViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyFormView and its DTO MyFormViewDTO.
 */
@Mapper(componentModel = "spring", uses = {MyEntityMapper.class, MyBranchMapper.class, MyReleaseMapper.class})
public interface MyFormViewMapper extends EntityMapper<MyFormViewDTO, MyFormView> {

    @Mapping(source = "myEntity.id", target = "myEntityId")
    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyFormViewDTO toDto(MyFormView myFormView); 

    @Mapping(source = "myEntityId", target = "myEntity")
    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyFormView toEntity(MyFormViewDTO myFormViewDTO);

    default MyFormView fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyFormView myFormView = new MyFormView();
        myFormView.setId(id);
        return myFormView;
    }
}
