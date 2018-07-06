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
import com.fubonlife.bio.exam.util.ExamUtil;
import com.fubonlife.bio.exam.util.SmartExtCommand;
import com.fubonlife.bio.exam.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/field")
public class FieldsController extends BaseController{

	@Autowired
	private FieldsService fieldsService;
	@Autowired
	private FormsService formsService;
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebRestResponse insertField(@RequestBody String data) throws Exception {
		System.out.println("insert Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = objectMapper.readValue(data, Map.class);
		Fields field = objectMapper.readValue(data, Fields.class);
		String lastId = "";
		Fields lastField = fieldsService.findTopByOrderByFieldIdDesc();
		if (lastField != null) {
			lastId = lastField.getFieldId();
		}
		map = new ExamUtil().setId(lastId, map, "field");
//		Fields last = fieldsService.findTopByOrderByCreatedDesc();
		field.setForm(formsService.read(field.getFormId()));
		field.setFieldId(map.get("fieldId"));
		fieldsService.create(field);
		return WebRestResponse.success(field,1);
	}
	
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public WebRestResponse read1(HttpServletRequest request) throws Exception {
		List<Fields> fields = fieldsService.readAll();
		List<Map<String,String>> list = new ArrayList<>();
		for(Fields field : fields){
			Users user = field.getForm().getSystem().getUser();
			Systems system = field.getForm().getSystem();
			Forms form = field.getForm();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map.put("fieldId", field.getFieldId());
			map.put("fieldLabel", field.getFieldLabel());
			map.put("systemName", system.getSystemName());
			map.put("systemId", system.getSystemId());
			map.put("formName", form.getFormName());
			map.put("formId", form.getFormId());
			map.put("account", user.getAccount());
			map.put("password", user.getPassword());
			map.put("userId", user.getUserId());
			list.add(map);
		}
//		return WebRestResponse.success(fields, fields.size());
		return WebRestResponse.success(list, list.size());
	}
	
	@RequestMapping(value = "/read2", method = RequestMethod.GET)
	public WebRestResponse read(HttpServletRequest request) throws Exception {
		System.out.println("limit" + request.getParameter("limit"));
		if (request.getParameter("criteria") != null) {
    		SmartExtCommand command = new SmartExtCommand(request);
	        Example<?> example = command.getExample(Fields.class);
	        Pageable pageable = getPageable(request);
	        Page<Fields> pages = fieldsService.findByExample(example, pageable);
	        return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
		}
	    Pageable pageable = getPageable(request);
	    Page<Fields> pages = fieldsService.findAll(pageable);
	    return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public WebRestResponse modifyField(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
//		Fields field = objectMapper.readValue(data, Fields.class);
		HashMap<String,String> map = objectMapper.readValue(data, HashMap.class);
		Fields field = fieldsService.read(map.get("fieldId"));
		field.setFieldLabel(map.get("fieldLabel"));
		field.setFieldType(map.get("fieldType"));
		List<Object> list = objectMapper.readValue(map.get("fieldItems"), List.class);
		System.out.println(list);
		field.setFieldItems(list);
		fieldsService.update(field);
		return WebRestResponse.success(field,1);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public WebRestResponse deleteField(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Fields field = objectMapper.readValue(data, Fields.class);
		fieldsService.delete(field.getFieldId());
		return WebRestResponse.success(field,1);
	}
	

}
