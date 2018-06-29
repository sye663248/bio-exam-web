package com.fubonlife.bio.mg.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.mg.entity.mongo.Form;
import com.fubonlife.bio.mg.entity.mongo.Users;
import com.fubonlife.bio.mg.service.FormService;
import com.fubonlife.bio.mg.service.FormsService;
import com.fubonlife.bio.mg.service.SystemsService;
import com.fubonlife.bio.mg.service.UsersService;
import com.fubonlife.bio.mg.util.WebRestResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/web/form")
public class UsersController {
	

	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(value = "/readUserById", method = RequestMethod.POST)
	public Users readUserById(HttpServletRequest request, @RequestBody String data) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		return usersService.read(user.getUserId());
	}
	
	@RequestMapping(value = "/readUser", method = RequestMethod.GET)
	public List<Users> readUser() throws Exception {
		return usersService.readAll();
	}
	
//	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public Users insertUser(@RequestBody String data) throws Exception {
//		System.out.println("class "+data.getClass());
		System.out.println("insert User " + data);
//		System.out.println("decode " + URLDecoder.decode(data));
//		System.out.println("encode " + URLEncoder.encode(data));
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		////////////
		List<Users> list = usersService.readAll();
		Users last = list.get(list.size()-1);
		Integer number = Integer.valueOf(last.getUserId().replaceAll("user", "")) + 1;
//		System.out.println("size " + number.toString().length());
		String id = "";
		for(int i = 0; i< 6-number.toString().length();i++){
			id += 0;
		}
		id = id + number;
		user.setUserId("user" + id);
		user.setKey("key" + id);
		usersService.create(user);
		return user;
	}
	
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public Users modifyUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify User " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		user.setKey(usersService.read(user.getUserId()).getKey());
		usersService.update(user);
		return user;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public Users deleteUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete User " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		usersService.delete(user.getUserId());
		return user;
	}
	
	@RequestMapping(value="/test", method = RequestMethod.POST)
	@ResponseBody
	public String test(String data) {
		System.out.println("ext test");
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		return data;
	}

}
