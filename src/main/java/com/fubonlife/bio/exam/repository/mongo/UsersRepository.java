package com.fubonlife.bio.exam.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.exam.entity.mongo.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	List<Users> findByAccountAndPassword(String account,String password);
	Users findTopByOrderByUserIdDesc();
}
