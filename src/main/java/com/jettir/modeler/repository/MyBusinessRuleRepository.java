package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyBusinessRule;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyBusinessRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyBusinessRuleRepository extends JpaRepository<MyBusinessRule, Long> {

}
