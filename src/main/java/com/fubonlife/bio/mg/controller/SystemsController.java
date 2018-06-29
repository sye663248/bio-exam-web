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
import com.fubonlife.bio.mg.entity.mongo.Systems;
import com.fubonlife.bio.mg.service.SystemsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/web/form")
public class SystemsController {
	
	@Autowired
	private SystemsService systemsService;
	
	@RequestMapping(value = "/readSystemById", method = RequestMethod.POST)
	public Systems readSystemById(HttpServletRequest request, @RequestBody String data) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Systems system = objectMapper.readValue(data, Systems.class);
		return systemsService.read(system.getSystemId());
	}
	
	@RequestMapping(value = "/readSystem", method = RequestMethod.GET)
	public List<Systems> readSystem() throws Exception {
		return systemsService.readAll();
	}
	
//	readByUserId readBySystemName
	@RequestMapping(value = "/readSystemByUserId", method = RequestMethod.POST)
	public List<Systems> readSystemByUserId(@RequestBody String userId) throws Exception {
		System.out.println("userId " + userId );
		ObjectMapper objectMapper = new ObjectMapper();
		Systems system = objectMapper.readValue(userId, Systems.class);
		System.out.println(system);
		System.out.println(system.getUserId());
		return systemsService.readSystemByUserId(system.getUserId());
//		return systemsService.readSystemByUserId(userId);
	}
	
	@RequestMapping(value = "/insertSystem", method = RequestMethod.POST)
	public Systems insertSystem(@RequestBody String data) throws Exception {
		System.out.println("insert system " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Systems system = objectMapper.readValue(data, Systems.class);
		List<Systems> list = systemsService.readAll();
		Systems last = list.get(list.size()-1);
		Integer number = Integer.valueOf(last.getSystemId().replaceAll("system", "")) + 1;
		String id = "";
		for(int i = 0; i< 6-number.toString().length();i++){
			id += 0;
		}
		id = id + number;
		system.setSystemId("system" + id);
		systemsService.create(system);
		return system;
	}
	
	@RequestMapping(value = "/modifySystem", method = RequestMethod.POST)
	public Systems modifyUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify System " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Systems system = objectMapper.readValue(data, Systems.class);
		systemsService.update(system);
		return system;
	}
	
	@RequestMapping(value = "/deleteSystem", method = RequestMethod.POST)
	public Systems deleteUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete System " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Systems system = objectMapper.readValue(data, Systems.class);
		systemsService.delete(system.getSystemId());
		return system;
	}
	
	

}
