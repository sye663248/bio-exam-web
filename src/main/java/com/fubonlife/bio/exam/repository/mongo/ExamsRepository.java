package com.fubonlife.bio.exam.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.exam.entity.mongo.Exams;

public interface ExamsRepository extends MongoRepository<Exams, String> {
	Exams findTopByOrderByExamIdDesc();
	
}
