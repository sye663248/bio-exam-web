package com.fubonlife.bio.mg.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fubonlife.bio.mg.entity.mongo.Test;
import com.fubonlife.bio.mg.service.TestService;
import com.fubonlife.bio.mg.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/test")
public class TestController {
	
	Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public WebRestResponse read(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//System.out.println(urlPrefix);
		
		log.info("Hello Loging....");
		
		Test o = testService.read();
		
		//testService.save();
		
		return WebRestResponse.success(o);
		
		
	}

}
