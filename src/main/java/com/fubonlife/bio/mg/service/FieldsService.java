package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.mg.entity.mongo.Fields;

public interface FieldsService {
	public List<Fields> readAll();
	public Fields read(String fieldId);
	public Fields create(Fields field);
	public Fields update(Fields field);
	public String delete(String fieldId);
	public List<Fields> readFieldByFormId(String formId);
	public Page<Fields> findByExample(Example example, Pageable pageable);
	public Page<Fields> findAll(Pageable pageable);
	public List<Fields> findAll();
	public Fields findTopByOrderByFieldIdDesc();
}
