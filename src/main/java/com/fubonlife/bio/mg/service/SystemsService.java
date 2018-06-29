package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.mg.entity.mongo.Systems;

public interface SystemsService {
	public List<Systems> readAll();
	public Systems read(String systemId);
	public Systems create(Systems system);
	public Systems update(Systems system);
	public String delete(String systemId);
	public List<Systems> readSystemByUserId(String userId);
	public Page<Systems> findByExample(Example example, Pageable pageable);
	public Page<Systems> findAll(Pageable pageable);
	public List<Systems> findAll();

}
