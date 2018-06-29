package com.fubonlife.bio.mg.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.mg.entity.mongo.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

}
