package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyRelease;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyRelease entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyReleaseRepository extends JpaRepository<MyRelease, Long> {

}
