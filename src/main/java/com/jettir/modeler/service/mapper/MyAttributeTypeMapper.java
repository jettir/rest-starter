package com.jettir.modeler.service.mapper;

import com.jettir.modeler.domain.*;
import com.jettir.modeler.service.dto.MyAttributeTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyAttributeType and its DTO MyAttributeTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MyAttributeTypeMapper extends EntityMapper<MyAttributeTypeDTO, MyAttributeType> {

    

    

    default MyAttributeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyAttributeType myAttributeType = new MyAttributeType();
        myAttributeType.setId(id);
        return myAttributeType;
    }
}
