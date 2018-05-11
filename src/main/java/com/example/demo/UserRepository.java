package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findByEmail(String email);
	public User findByFbid(String fbid);
	public List<User> findAll();

}
