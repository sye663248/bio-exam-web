package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.mg.entity.mongo.Form;

public interface FormService {
	public List<Form> readAll();

	public Form read();

	public Form create(Form form);

	public Form update(Form form);

	public Form delete(Form form);

	public Page<Form> findByExample(Example example, Pageable pageable);

	public Page<Form> findAll(Pageable pageable);

	public List<Form> findAll();

	public List<Form> findByName(String name);
}
