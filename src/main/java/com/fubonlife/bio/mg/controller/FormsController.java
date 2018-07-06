package com.fubonlife.bio.mg.controller;

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
import com.fubonlife.bio.mg.entity.mongo.Fields;
import com.fubonlife.bio.mg.entity.mongo.Forms;
import com.fubonlife.bio.mg.entity.mongo.Systems;
import com.fubonlife.bio.mg.entity.mongo.Users;
import com.fubonlife.bio.mg.service.FieldsService;
import com.fubonlife.bio.mg.service.FormsService;
import com.fubonlife.bio.mg.service.SystemsService;
import com.fubonlife.bio.mg.service.UsersService;
import com.fubonlife.bio.mg.util.ExamUtil;
import com.fubonlife.bio.mg.util.SmartExtCommand;
import com.fubonlife.bio.mg.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/form")
public class FormsController extends BaseController{
	
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
	public WebRestResponse insertForm(@RequestBody String data) throws Exception {
		System.out.println("insert Form " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = objectMapper.readValue(data, Map.class);
		Forms form = objectMapper.readValue(data, Forms.class);
		String lastId = "";
		Forms lastForm = formsService.findTopByOrderByFormIdDesc();
		if (lastForm != null) {
			lastId = lastForm.getFormId();
		}
		map = new ExamUtil().setId(lastId, map, "form");
		form.setSystem(systemsService.read(form.getSystemId()));
		form.setFormId(map.get("formId"));
		formsService.create(form);
		return WebRestResponse.success(form);
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
				map.put("formId", null);
				map.put("formName", null);
				map.put("systemId", null);
				map.put("systemName", null);
				list.add(map);
			} else {
				for (Systems systema : systems) {
					List<Forms> formList = formsService.readFormBySystemId(systema.getSystemId());
					if (formList.size() == 0) {
						Map<String, String> map = new LinkedHashMap<String, String>();
						map.put("userId", usera.getUserId());
						map.put("account", usera.getAccount());
						map.put("password", usera.getPassword());
						map.put("formId", null);
						map.put("formName", null);
						map.put("systemId", systema.getSystemId());
						map.put("systemName", systema.getSystemName());
						list.add(map);
					} else {
						for (Forms form : formList) {
							Systems system = form.getSystem();
							Map<String, String> map = new LinkedHashMap<String, String>();
							map.put("userId", usera.getUserId());
							map.put("account", usera.getAccount());
							map.put("password", usera.getPassword());
							map.put("formId", form.getFormId());
							map.put("formName", form.getFormName());
							map.put("systemId", system.getSystemId());
							map.put("systemName", system.getSystemName());
							list.add(map);
						}
					}
				}
			}
		}
		return WebRestResponse.success(list, list.size());
		

	}
	
	
	@RequestMapping(value = "/read2", method = RequestMethod.GET)
	public WebRestResponse read(HttpServletRequest request) throws Exception {
		System.out.println("limit" + request.getParameter("limit"));
		if (request.getParameter("criteria") != null) {
    		SmartExtCommand command = new SmartExtCommand(request);
	        Example<?> example = command.getExample(Forms.class);
//	        Example<UserData> example = (Example<UserData>)command.getExample(UserData.class);
	        Pageable pageable = getPageable(request);
	        Page<Forms> pages = formsService.findByExample(example, pageable);
//	        Page<UserData> pages = userDataRepository.findAll(example, pageable);
	        return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
		}

	    Pageable pageable = getPageable(request);
	    Page<Forms> pages = formsService.findAll(pageable);
	    return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public WebRestResponse modifyForm(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify Form " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String,String> map = objectMapper.readValue(data, HashMap.class);
//		Forms form = objectMapper.readValue(data, Forms.class);
		Forms form = formsService.read(map.get("formId"));
		form.setFormName(map.get("formName"));
		formsService.update(form);
		return WebRestResponse.success(form);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public WebRestResponse deleteForm(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete Form " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(data, Map.class);
		String formId = (String) map.get("formId");
		Forms form = formsService.read(formId);
//		Forms form = objectMapper.readValue(data, Forms.class);
		formsService.delete(formId);
		//field
		List<Fields> fields = fieldsService.readFieldByFormId(formId);
		for(Fields field : fields){
			fieldsService.delete(field.getFieldId());
		}
		return WebRestResponse.success(form);
	}

}
