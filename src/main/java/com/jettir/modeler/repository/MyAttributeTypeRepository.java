package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyAttributeType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyAttributeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyAttributeTypeRepository extends JpaRepository<MyAttributeType, Long> {

}
