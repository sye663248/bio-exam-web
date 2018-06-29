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
import com.fubonlife.bio.mg.entity.mongo.Fields;
import com.fubonlife.bio.mg.service.FieldsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/web/form")
public class FieldsController {

	@Autowired
	private FieldsService fieldsService;
	
	@RequestMapping(value = "/readFieldById", method = RequestMethod.POST)
	public Fields readFieldById(HttpServletRequest request, @RequestBody String data) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Fields field = objectMapper.readValue(data, Fields.class);
		return fieldsService.read(field.getFieldId());
	}
	
	@RequestMapping(value = "/readField", method = RequestMethod.GET)
	public List<Fields> readField() throws Exception {
		return fieldsService.readAll();
	}
	
	@RequestMapping(value = "/insertField", method = RequestMethod.POST)
	public Fields insertField(@RequestBody String data) throws Exception {
		System.out.println("insert Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Fields field = objectMapper.readValue(data, Fields.class);
		List<Fields> list = fieldsService.readAll();
		Fields last = list.get(list.size()-1);
		Integer number = Integer.valueOf(last.getFieldId().replaceAll("field", "")) + 1;
		String id = "";
		for(int i = 0; i< 6-number.toString().length();i++){
			id += 0;
		}
		id = id + number;
		field.setFieldId("field" + id);
		fieldsService.create(field);
		return field;
	}
	
	@RequestMapping(value = "/modifyField", method = RequestMethod.POST)
	public Fields modifyField(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Fields field = objectMapper.readValue(data, Fields.class);
		fieldsService.update(field);
		return field;
	}
	
	@RequestMapping(value = "/deleteField", method = RequestMethod.POST)
	public Fields deleteField(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Fields field = objectMapper.readValue(data, Fields.class);
		fieldsService.delete(field.getFieldId());
		return field;
	}
}
