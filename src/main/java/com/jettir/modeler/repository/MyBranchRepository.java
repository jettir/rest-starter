package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyBranch;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MyBranch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyBranchRepository extends JpaRepository<MyBranch, Long> {

}
