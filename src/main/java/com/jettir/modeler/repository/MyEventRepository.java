package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyEvent;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyEventRepository extends JpaRepository<MyEvent, Long> {

}
