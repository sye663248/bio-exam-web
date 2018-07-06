package com.fubonlife.bio.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.exam.entity.mongo.Fields;
import com.fubonlife.bio.exam.entity.mongo.Forms;
import com.fubonlife.bio.exam.entity.mongo.Systems;
import com.fubonlife.bio.exam.entity.mongo.Users;
import com.fubonlife.bio.exam.service.FieldsService;
import com.fubonlife.bio.exam.service.FormsService;
import com.fubonlife.bio.exam.service.SystemsService;
import com.fubonlife.bio.exam.service.UsersService;
import com.fubonlife.bio.exam.util.ExamUtil;
import com.fubonlife.bio.exam.util.SmartExtCommand;
import com.fubonlife.bio.exam.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/system")
public class SystemsController extends BaseController{
	
	@Autowired
	private FormsService formsService;
	@Autowired
	private SystemsService systemsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private FieldsService fieldsService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebRestResponse insertSystem(@RequestBody String data) throws Exception {
		System.out.println("insert system " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Systems system = objectMapper.readValue(data, Systems.class);
		Map<String,String> map = objectMapper.readValue(data, Map.class);
		String lastId = "";
		Systems lastSystem = systemsService.findTopByOrderBySystemIdDesc();
		if (lastSystem != null) {
			lastId = lastSystem.getSystemId();
		}
		map = new ExamUtil().setId(lastId, map, "system");
		system.setUser(usersService.read(system.getUserId()));
		system.setSystemId(map.get("systemId"));
		systemsService.create(system);
		return WebRestResponse.success(system);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public WebRestResponse read1(HttpServletRequest request) throws Exception {
		List<Users> users = usersService.readAll();
		List<Map<String, String>> list = new ArrayList<>();
		for (Users usera : users) {
			
			List<Systems> systems = systemsService.readSystemByUserId(usera.getUserId());
			
			if (systems.size() == 0) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("userId", usera.getUserId());
				map.put("account", usera.getAccount());
				map.put("password", usera.getPassword());
				map.put("systemId", null);
				map.put("systemName", null);
				list.add(map);
			} else {
				for (Systems system : systems) {
					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put("userId", usera.getUserId());
					map.put("account", usera.getAccount());
					map.put("password", usera.getPassword());
					map.put("systemId", system.getSystemId());
					map.put("systemName", system.getSystemName());
					list.add(map);
				}
			}
		}
		return WebRestResponse.success(list, list.size());

	}
	
	@RequestMapping(value = "/read2", method = RequestMethod.GET)
	public WebRestResponse read(HttpServletRequest request) throws Exception {
		if (request.getParameter("criteria") != null) {
    		SmartExtCommand command = new SmartExtCommand(request);
	        Example<?> example = command.getExample(Map.class);
	        Pageable pageable = getPageable(request);
	        Page<?> pages = systemsService.findByExample(example, pageable);
	        return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
		}
	    Pageable pageable = getPageable(request);
	    Page<Systems> pages = systemsService.findAll(pageable);
	    return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public WebRestResponse modifyUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify System " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String,String> map = objectMapper.readValue(data, HashMap.class);
		Systems system = systemsService.read(map.get("systemId"));
		system.setSystemName(map.get("systemName"));
		systemsService.update(system);
		return WebRestResponse.success(system);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public WebRestResponse deleteUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete System " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(data, Map.class);
		String systemId = (String) map.get("systemId");
		Systems system = systemsService.read(systemId);
		systemsService.delete(systemId);
		List<Forms> forms = formsService.readFormBySystemId(systemId);
		for ( Forms form : forms ){
			String formId = form.getFormId();
			formsService.delete(formId);
			List<Fields> fields = fieldsService.readFieldByFormId(formId);
			for(Fields field : fields){
				fieldsService.delete(field.getFieldId());
			}
		}
		return WebRestResponse.success(system);
	}
	

}
