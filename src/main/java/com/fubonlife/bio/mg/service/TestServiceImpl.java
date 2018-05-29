package com.fubonlife.bio.mg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.mg.controller.TestController;
import com.fubonlife.bio.mg.entity.mongo.Test;

@Service
public class TestServiceImpl implements TestService{
	
	Logger log = LoggerFactory.getLogger(TestController.class);

	@Override
	public void readAll() {
		log.info("It is service layer!!");
		
	}

	@Override
	public Test read() {
		
		Test o = new Test();
		
		o.setTestId("Test001");
		o.setName("Test Name");
		o.setEmail("shangchun.lin@fubon.com");
		o.setAge(35);
		o.setEnabled(true);
		
		return o;
	}

}
