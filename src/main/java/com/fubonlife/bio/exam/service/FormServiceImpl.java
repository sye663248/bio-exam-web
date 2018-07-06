package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.entity.mongo.Form;
import com.fubonlife.bio.mg.repository.mongo.FormRespository;

@Service
public class FormServiceImpl implements FormService{

	@Autowired
	FormRespository formRepository;
	
	@Override
	public List<Form> readAll() {
		
		return formRepository.findAll();
	}

	@Override
	public Form read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form create(Form form) {
		// TODO Auto-generated method stub
		formRepository.save(form);
		return form;
	}

	@Override
	public Form update(Form form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form delete(Form form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Form> findByExample(Example example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Form> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Form> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Form> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
