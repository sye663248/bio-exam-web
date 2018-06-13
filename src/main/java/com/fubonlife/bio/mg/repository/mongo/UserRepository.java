package com.fubonlife.bio.mg.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fubonlife.bio.mg.entity.mongo.User;;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}

