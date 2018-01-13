package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyProcess;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyProcessRepository extends JpaRepository<MyProcess, Long> {

}
