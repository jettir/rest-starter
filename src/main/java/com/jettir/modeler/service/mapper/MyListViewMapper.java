package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyListViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyListView and its DTO MyListViewDTO.
 */
@Mapper(componentModel = "spring", uses = {MyEntityMapper.class, MyBranchMapper.class, MyReleaseMapper.class})
public interface MyListViewMapper extends EntityMapper<MyListViewDTO, MyListView> {

    @Mapping(source = "myEntity.id", target = "myEntityId")
    @Mapping(source = "myBranch.id", target = "myBranchId")
    @Mapping(source = "myRelease.id", target = "myReleaseId")
    MyListViewDTO toDto(MyListView myListView); 

    @Mapping(source = "myEntityId", target = "myEntity")
    @Mapping(source = "myBranchId", target = "myBranch")
    @Mapping(source = "myReleaseId", target = "myRelease")
    MyListView toEntity(MyListViewDTO myListViewDTO);

    default MyListView fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyListView myListView = new MyListView();
        myListView.setId(id);
        return myListView;
    }
}
