package com.fubonlife.bio.mg.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.mg.entity.mongo.Systems;

public interface SystemsRepository extends MongoRepository<Systems, String>{

	List<Systems> findByUserId(String userId);

}
