package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyListView;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyListView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyListViewRepository extends JpaRepository<MyListView, Long> {

}
