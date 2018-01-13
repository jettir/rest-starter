package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyEventType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyEventType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyEventTypeRepository extends JpaRepository<MyEventType, Long> {

}
