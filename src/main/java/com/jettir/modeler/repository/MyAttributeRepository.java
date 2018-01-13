package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyAttribute;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyAttributeRepository extends JpaRepository<MyAttribute, Long> {

}
