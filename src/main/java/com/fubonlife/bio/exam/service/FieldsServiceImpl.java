package com.fubonlife.bio.exam.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.exam.entity.mongo.Fields;
import com.fubonlife.bio.exam.repository.mongo.FieldsRepository;
import com.mongodb.BasicDBObject;

@Service
public class FieldsServiceImpl implements FieldsService{

	@Autowired
	FieldsRepository fieldsRepository;
//	MongoTemplate mongoTemplate;
//	Aggregation  aggregation; 
//	TypedAggregation<BasicDBObject> agg;
	
	@Override
	public List<Fields> readAll() {
//		agg = Aggregation.newAggregation(BasicDBObject.class,
//				Aggregation.group("forms").count().as("num"),
//				Aggregation.project("num").and("onlineStatus").previousOperation());
//		System.out.println("agg "+agg.toString());
//		AggregationResults<BasicDBObject> ar = mongoTemplate.aggregate(agg, BasicDBObject.class);
//		System.out.println("ar " + ar.toString());
		
//		List<Fields> list = fieldsRepository.findAll();
//		List<String> data = list.get(1).getData();
//		ObjectMapper objectMapper = new ObjectMapper();
//		StringWriter sw =new StringWriter();
//		try {
//			objectMapper.writeValue(sw, data);
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("data " + data);
//		System.out.println("swdata "+sw.toString());
		return fieldsRepository.findAll();
	}

	@Override
	public Fields read(String fieldId) {
		return fieldsRepository.findById(fieldId).get();
	}
	
	@Override
	public List<Fields> readFieldByFormId(String formId) {
		return fieldsRepository.findByFormId(formId);
	}

	@Override
	public Fields create(Fields field) {
		return fieldsRepository.insert(field);
	}

	@Override
	public Fields update(Fields field) {
		return fieldsRepository.save(field);
	}

	@Override
	public String delete(String fieldId) {
		fieldsRepository.deleteById(fieldId);
		return fieldId;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Fields> findByExample(Example example, Pageable pageable) {
		return fieldsRepository.findAll(example, pageable);
	}

	@Override
	public Page<Fields> findAll(Pageable pageable) {
		return fieldsRepository.findAll(pageable);
	}

	@Override
	public List<Fields> findAll() {
		return fieldsRepository.findAll();
	}

	@Override
	public Fields findTopByOrderByFieldIdDesc() {
		return fieldsRepository.findTopByOrderByFieldIdDesc();
	}

	

}
