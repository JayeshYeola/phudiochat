package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer>{
	public Comment findById(int Id);
	public List<Comment> findByPostid(int postid);
	public List<Comment> findAll();
}
