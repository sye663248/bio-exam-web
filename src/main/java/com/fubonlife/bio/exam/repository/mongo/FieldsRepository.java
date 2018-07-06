package com.fubonlife.bio.exam.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fubonlife.bio.exam.entity.mongo.Fields;
import com.fubonlife.bio.exam.entity.mongo.Forms;

public interface FieldsRepository extends MongoRepository<Fields, String> {

	List<Fields> findByFormId(String formId);
	Fields findTopByOrderByFieldIdDesc();
}
