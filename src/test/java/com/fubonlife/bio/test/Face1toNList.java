package com.fubonlife.bio.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fubonlife.model.NList;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1toNList {

	Logger log = LoggerFactory.getLogger(Face1toNList.class);
	
	@Test
	public void test01() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://223.22.252.52:803/group/list";
		ResponseEntity<NList> response = restTemplate.getForEntity(url, NList.class);
		
		NList o = response.getBody();
		
		
		
		log.info("============================================");
//        for(int i =0 ;i < o.getGroups().length ;i++) {
//        	log.info("groups: "+o.getGroups()[i]);
//        }
        for(String groups : o.getGroups()){ 
        	log.info("groups: "+groups);
        }              
		log.info("result: "+o.getResult());
		log.info("error: "+o.getError());
		log.info("============================================");


	}
	
}
