package com.jettir.modeler.repository;

import org.springframework.data.repository.CrudRepository;

import com.jettir.modeler.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
