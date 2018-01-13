package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyAttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyAttribute and its DTO MyAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {MyEntityMapper.class, MyAttributeTypeMapper.class})
public interface MyAttributeMapper extends EntityMapper<MyAttributeDTO, MyAttribute> {

    @Mapping(source = "myEntity.id", target = "myEntityId")
    @Mapping(source = "myAttributeType.id", target = "myAttributeTypeId")
    MyAttributeDTO toDto(MyAttribute myAttribute); 

    @Mapping(source = "myEntityId", target = "myEntity")
    @Mapping(source = "myAttributeTypeId", target = "myAttributeType")
    MyAttribute toEntity(MyAttributeDTO myAttributeDTO);

    default MyAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyAttribute myAttribute = new MyAttribute();
        myAttribute.setId(id);
        return myAttribute;
    }
}
