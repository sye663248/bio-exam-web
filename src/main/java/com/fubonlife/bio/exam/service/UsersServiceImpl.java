package com.fubonlife.bio.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.exam.entity.mongo.Users;
import com.fubonlife.bio.exam.repository.mongo.UsersRepository;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Users> findByExample(Example example, Pageable pageable) {
		return usersRepository.findAll(example, pageable);
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Users readByAccountAndPasssword(String account, String password) {
		List<Users> users = usersRepository.findByAccountAndPassword(account, password);
		if (users.size()==0){
			return null;
		}
		return usersRepository.findByAccountAndPassword(account, password).get(0);
	}

	@Override
	public Users findTopByOrderByUserIdDesc() {
		return usersRepository.findTopByOrderByUserIdDesc();
	}

}
