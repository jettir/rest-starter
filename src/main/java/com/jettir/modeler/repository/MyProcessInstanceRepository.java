package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyProcessInstance;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyProcessInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyProcessInstanceRepository extends JpaRepository<MyProcessInstance, Long> {

}
