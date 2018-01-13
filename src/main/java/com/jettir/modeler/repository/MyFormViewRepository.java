package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyFormView;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyFormView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyFormViewRepository extends JpaRepository<MyFormView, Long> {

}
