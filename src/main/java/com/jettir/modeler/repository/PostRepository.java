package com.jettir.modeler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jettir.modeler.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	Post findFirstByOrderByPostedOnDesc();

	List<Post> findAllByOrderByPostedOnDesc();

	Post findBySlug(String slug);

}
