package com.vidya.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vidya.api.db.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	public User findUserByUsername(@Param("username") String username);
}