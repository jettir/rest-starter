package com.jettir.modeler.repository;

import com.jettir.modeler.domain.MyEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the MyEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
    @Query("select distinct my_entity from MyEntity my_entity left join fetch my_entity.myBusinessRules")
    List<MyEntity> findAllWithEagerRelationships();

    @Query("select my_entity from MyEntity my_entity left join fetch my_entity.myBusinessRules where my_entity.id =:id")
    MyEntity findOneWithEagerRelationships(@Param("id") Long id);

}
