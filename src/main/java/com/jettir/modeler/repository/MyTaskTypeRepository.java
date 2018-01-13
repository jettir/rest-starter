package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyTaskType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyTaskType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyTaskTypeRepository extends JpaRepository<MyTaskType, Long> {

}
