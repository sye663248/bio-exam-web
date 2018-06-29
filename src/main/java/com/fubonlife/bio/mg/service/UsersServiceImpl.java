package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.entity.mongo.Users;
import com.fubonlife.bio.mg.repository.mongo.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public List<Users> readAll() {
		return usersRepository.findAll();
	}

	@Override
	public Users read(String userId) {
		return usersRepository.findById(userId).get();
	}

	@Override
	public Users create(Users user) {
		return usersRepository.insert(user);
	}

	@Override
	public Users update(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public String delete(String userId) {
		usersRepository.deleteById(userId);
		return userId;
	}

	@Override
	public Page<Users> findByExample(Example example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
