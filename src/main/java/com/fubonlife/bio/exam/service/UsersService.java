package com.fubonlife.bio.exam.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.exam.entity.mongo.Users;

public interface UsersService {

	public List<Users> readAll();
	public Users read(String userId);
	public Users create(Users user);
	public Users update(Users user);
	public String delete(String userId);
	public Page<Users> findByExample(Example example, Pageable pageable);
	public Page<Users> findAll(Pageable pageable);
	public List<Users> findAll();
	public Users readByAccountAndPasssword(String account, String password);
	public Users findTopByOrderByUserIdDesc();
}
