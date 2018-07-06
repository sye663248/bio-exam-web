package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.entity.mongo.Systems;
import com.fubonlife.bio.mg.entity.mongo.Users;
import com.fubonlife.bio.mg.repository.mongo.SystemsRepository;

@Service
public class SystemsServiceImpl implements SystemsService{

	@Autowired
	SystemsRepository systemsRepository;
	
	@Override
	public List<Systems> readAll() {
		return systemsRepository.findAll();
	}

	@Override
	public Systems read(String systemId) {
		return systemsRepository.findById(systemId).get();
	}

	@Override
	public Systems create(Systems system) {
		return systemsRepository.insert(system);
	}

	@Override
	public Systems update(Systems system) {
		return systemsRepository.save(system);
	}

	@Override
	public String delete(String systemId) {
		systemsRepository.deleteById(systemId);
		return systemId;
	}
	
	@Override
	public List<Systems> readSystemByUserId(String userId) {
		return systemsRepository.findByUserId(userId);
	}
	
	public List<Systems> readSystemByUser(Users user) {
		return systemsRepository.findByUser(user);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Systems> findByExample(Example example, Pageable pageable) {
		return systemsRepository.findAll(example, pageable);
	}

	@Override
	public Page<Systems> findAll(Pageable pageable) {
		return systemsRepository.findAll(pageable);
	}

	@Override
	public List<Systems> findAll() {
		return systemsRepository.findAll();
	}

	@Override
	public Systems findTopByOrderBySystemIdDesc() {
		return systemsRepository.findTopByOrderBySystemIdDesc();
	}

	

}
