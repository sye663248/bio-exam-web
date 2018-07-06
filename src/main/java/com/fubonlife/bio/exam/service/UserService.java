package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.mg.entity.mongo.User;

public interface UserService {

	public List<User> readAll();

	public User read(String userId);

	public User create(User obj);

	public User update(User obj);

	public User delete(String systemName);

	public Page<User> findByExample(Example example, Pageable pageable);

	public Page<User> findAll(Pageable pageable);

	public List<User> findAll();

	public List<User> findByAccount(String account);
}
