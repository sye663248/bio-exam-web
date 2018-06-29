package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.entity.mongo.Fields;
import com.fubonlife.bio.mg.repository.mongo.FieldsRepository;

@Service
public class FieldsServiceImpl implements FieldsService{

	@Autowired
	FieldsRepository fieldsRepository;
	
	@Override
	public List<Fields> readAll() {
		return fieldsRepository.findAll();
	}

	@Override
	public Fields read(String fieldId) {
		return fieldsRepository.findById(fieldId).get();
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

	@Override
	public Page<Fields> findByExample(Example example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Fields> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fields> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
