package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.mg.entity.mongo.Forms;

public interface FormsService {
	public List<Forms> readAll();
	public Forms read(String formId);
	public Forms create(Forms form);
	public Forms update(Forms form);
	public String delete(String formId);
	public Page<Forms> findByExample(Example example, Pageable pageable);
	public Page<Forms> findAll(Pageable pageable);
	public List<Forms> findAll();
	public List<Forms> readFormBySystemId(String systemId);
	public Forms findTopByOrderByFormIdDesc();
}
