package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.entity.mongo.Forms;
import com.fubonlife.bio.mg.repository.mongo.FormsRepository;

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
	public Page<Forms> findByExample(Example example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Forms> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Forms> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
