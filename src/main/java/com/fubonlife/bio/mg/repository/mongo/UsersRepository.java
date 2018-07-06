package com.fubonlife.bio.mg.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.mg.entity.mongo.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	List<Users> findByAccountAndPassword(String account,String password);
	Users findTopByOrderByUserIdDesc();
}
