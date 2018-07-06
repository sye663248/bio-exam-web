package com.fubonlife.bio.mg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.mg.entity.mongo.Form;
import com.fubonlife.bio.mg.entity.mongo.User;
import com.fubonlife.bio.mg.service.FormService;
import com.fubonlife.bio.mg.service.UserService;
import com.fubonlife.bio.mg.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/form")
public class FormController {
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/readUserByName", method = RequestMethod.POST)
	public User readUserByName(HttpServletRequest request, @RequestBody String data) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(data, User.class);
		System.out.println("username " + user.getName());
		return userService.findByName(user.getName()).get(0);
	}
	
	@RequestMapping(value = "/readUser", method = RequestMethod.GET)
	public List<User> readUser() throws Exception {
		return userService.readAll();
	}
	
	@RequestMapping(value = "/insertForm", method = RequestMethod.POST)
	public WebRestResponse insertForm(@RequestBody Object data) throws Exception {
		Form form = new Form();
//		form.setId("");
		form.setData(data);
		formService.create(form);
		return WebRestResponse.success(data);
	}
	
	@RequestMapping(value = "/readAllForm", method = RequestMethod.GET)
	public List<Form> readAllForm() throws Exception {
		
		return formService.readAll();
	}

}
