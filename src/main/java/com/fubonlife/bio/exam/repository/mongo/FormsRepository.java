package com.fubonlife.bio.exam.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.exam.entity.mongo.Forms;

public interface FormsRepository extends MongoRepository<Forms, String>{
	List<Forms> findBySystemId(String systemId);
	Forms findTopByOrderByFormIdDesc();
}
