package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyParameterType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyParameterType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyParameterTypeRepository extends JpaRepository<MyParameterType, Long> {

}
