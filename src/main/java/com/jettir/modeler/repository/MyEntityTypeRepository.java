package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyEntityType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyEntityType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyEntityTypeRepository extends JpaRepository<MyEntityType, Long> {

}
