package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.entity.mongo.User;
import com.fubonlife.bio.mg.repository.mongo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> readAll() {
		return userRepository.findAll();
	}

	@Override
	public User read(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findAllByUserId(userId);
	}

	@Override
	public User create(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User update(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User delete(String systemName) {
		// TODO Auto-generated method stub
		return userRepository.deleteBySystemName(systemName).get(0);
	}

	@Override
	public Page<User> findByExample(Example example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByAccount(String account) {
		return userRepository.findByAccount(account);
	}

}
