package com.fubonlife.bio.exam.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.exam.entity.mongo.Systems;
import com.fubonlife.bio.exam.entity.mongo.Users;

public interface SystemsRepository extends MongoRepository<Systems, String>{
	List<Systems> findByUserId(String userId);
	List<Systems> findByUser(Users user);
	Systems findTopByOrderBySystemIdDesc();
}
