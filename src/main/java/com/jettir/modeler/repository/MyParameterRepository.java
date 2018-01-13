package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyParameter;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyParameterRepository extends JpaRepository<MyParameter, Long> {

}
