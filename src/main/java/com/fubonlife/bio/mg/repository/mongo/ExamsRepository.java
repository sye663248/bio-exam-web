package com.fubonlife.bio.mg.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.mg.entity.mongo.Exams;

public interface ExamsRepository extends MongoRepository<Exams, String> {
	Exams findTopByOrderByExamIdDesc();
	
}
