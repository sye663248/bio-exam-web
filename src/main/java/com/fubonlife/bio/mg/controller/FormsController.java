package com.fubonlife.bio.mg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.mg.entity.mongo.Forms;
import com.fubonlife.bio.mg.entity.mongo.Users;
import com.fubonlife.bio.mg.service.FormsService;
import com.fubonlife.bio.mg.util.WebRestResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/web/form")
public class FormsController {
	
	@Autowired
	private FormsService formsService;
	

	@RequestMapping(value = "/readFormById", method = RequestMethod.POST)
	public Forms readFormById(HttpServletRequest request, @RequestBody String data) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Forms form = objectMapper.readValue(data, Forms.class);
		return formsService.read(form.getFormId());
	}
	
	@RequestMapping(value = "/readForm", method = RequestMethod.GET)
	public List<Forms> readForm() throws Exception {
		return formsService.readAll();
	}
	
	@RequestMapping(value = "/insertForm", method = RequestMethod.POST)
	public Forms insertForm(@RequestBody String data) throws Exception {
		System.out.println("insert Form " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Forms form = objectMapper.readValue(data, Forms.class);
		List<Forms> list = formsService.readAll();
		Forms last = list.get(list.size()-1);
		Integer number = Integer.valueOf(last.getFormId().replaceAll("form", "")) + 1;
		String id = "";
		for(int i = 0; i< 6-number.toString().length();i++){
			id += 0;
		}
		id = id + number;
		form.setFormId("form" + id);
		formsService.create(form);
		return form;
	}
	
	@RequestMapping(value = "/modifyForm", method = RequestMethod.POST)
	public Forms modifyForm(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify Form " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Forms form = objectMapper.readValue(data, Forms.class);
		formsService.update(form);
		return form;
	}
	
	@RequestMapping(value = "/deleteForm", method = RequestMethod.POST)
	public Forms deleteForm(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete Form " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Forms form = objectMapper.readValue(data, Forms.class);
		formsService.delete(form.getFormId());
		return form;
	}

}
