package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyTask;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyTaskRepository extends JpaRepository<MyTask, Long> {

}
