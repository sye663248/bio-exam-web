package com.fubonlife.bio.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.exam.entity.mongo.Forms;
import com.fubonlife.bio.exam.entity.mongo.Systems;
import com.fubonlife.bio.exam.repository.mongo.FormsRepository;

@Service
public class FormsServiceImpl implements FormsService{

	@Autowired
	FormsRepository formsRepository;

	@Override
	public List<Forms> readAll() {
		return formsRepository.findAll();
	}

	@Override
	public Forms read(String formId) {
		return formsRepository.findById(formId).get();
	}

	@Override
	public Forms create(Forms form) {
		return formsRepository.insert(form);
	}

	@Override
	public Forms update(Forms form) {
		return formsRepository.save(form);
	}

	@Override
	public String delete(String formId) {
		formsRepository.deleteById(formId);
		return formId;
	}
	
	@Override
	public List<Forms> readFormBySystemId(String systemId) {
		return formsRepository.findBySystemId(systemId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Forms> findByExample(Example example, Pageable pageable) {
		return formsRepository.findAll(example, pageable);
	}

	@Override
	public Page<Forms> findAll(Pageable pageable) {
		return formsRepository.findAll(pageable);
	}

	@Override
	public List<Forms> findAll() {
		return formsRepository.findAll();
	}

	@Override
	public Forms findTopByOrderByFormIdDesc() {
		return formsRepository.findTopByOrderByFormIdDesc();
	}

	
	
	

}
