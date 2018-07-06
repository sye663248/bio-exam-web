package com.fubonlife.bio.mg.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.fubonlife.bio.mg.util.WebRestResponse;
import com.fubonlife.bio.mg.util.ExamUtil;
import com.fubonlife.bio.mg.util.SmartExtCommand;

@RestController
@RequestMapping(value="/web/user")
public class UsersController extends BaseController{
	

	@Autowired
	private UsersService usersService;
	@Autowired
	private SystemsService systemsService;
	@Autowired
	private FormsService formsService; 
	@Autowired
	private FieldsService fieldsService;

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebRestResponse insertUser(@RequestBody String data) throws Exception {
		System.out.println("insert User " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		Map<String,String> map = objectMapper.readValue(data, Map.class);
		List<Users> list = usersService.readAll();
		if(list.contains(user)){
			return WebRestResponse.failure("the same account");
		}
		String lastId = "";
		Users lastUser = usersService.findTopByOrderByUserIdDesc();
		if (lastUser != null) {
			lastId = lastUser.getUserId();
		}
//		if (list.size()!=0){
//			lastId = list.get(list.size()-1).getUserId();
//		}
		System.out.println("map " + map);
		map = new ExamUtil().setId(lastId, map, "user");
		System.out.println("map2 " + map);
		user.setUserId(map.get("userId"));
		user.setKey(map.get("keyId"));
		usersService.create(user);
		return WebRestResponse.success(user);
	}
	
	@RequestMapping(value = "/read1", method = RequestMethod.GET)
	public WebRestResponse read1(HttpServletRequest request) throws Exception {
		List<Users> list = usersService.readAll();
		Users lastUser = usersService.findTopByOrderByUserIdDesc();
		System.out.println("lastuser " + lastUser.getUserId());
		return WebRestResponse.success(list, list.size());
	}
	
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public WebRestResponse read(HttpServletRequest request) throws Exception {
		System.out.println("criteria" + request.getParameter("criteria"));
		if (request.getParameter("criteria") != null) {
    		SmartExtCommand command = new SmartExtCommand(request);
	        Example<?> example = command.getExample(Users.class);
	        Pageable pageable = getPageable(request);
	        Page<Users> pages = usersService.findByExample(example, pageable);
	        return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
		}

	    Pageable pageable = getPageable(request);
	    Page<Users> pages = usersService.findAll(pageable);
	    return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public WebRestResponse modifyUser(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("modify User " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String,String> map = objectMapper.readValue(data, HashMap.class);
//		Users user = objectMapper.readValue(data, Users.class);
		Users user = usersService.read(map.get("userId"));
		user.setAccount(map.get("account"));
		user.setPassword(map.get("password"));
		usersService.update(user);
		return WebRestResponse.success(user);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public WebRestResponse deleteUser(@RequestBody String data) throws Exception {
		System.out.println("delete User " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		String userId = user.getUserId();
		usersService.delete(userId);
		List<Systems> systems = systemsService.readSystemByUserId(userId);
		for( Systems system : systems ){
			String systemId = system.getSystemId();
			systemsService.delete(systemId);
			List<Forms> forms = formsService.readFormBySystemId(systemId);
			for ( Forms form : forms ){
				String formId = form.getFormId();
				formsService.delete(formId);
				//field
				List<Fields> fields = fieldsService.readFieldByFormId(formId);
				for(Fields field : fields){
					fieldsService.delete(field.getFieldId());
				}
				
			}
		}
		return WebRestResponse.success(user);
	}
	
//	login
	@RequestMapping(value = "/readUserByAccountAndPassword", method = RequestMethod.POST)
	public Users readUserByAccountAndPasssword(HttpServletRequest request, @RequestBody String data) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(data, Users.class);
		user = usersService.readByAccountAndPasssword(user.getAccount(), user.getPassword());
		System.out.println("user " + user.getUserId());
		return user;
	}
	


}
