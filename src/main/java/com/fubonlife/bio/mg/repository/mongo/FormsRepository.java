package com.fubonlife.bio.mg.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.mg.entity.mongo.Forms;

public interface FormsRepository extends MongoRepository<Forms, String>{

}
