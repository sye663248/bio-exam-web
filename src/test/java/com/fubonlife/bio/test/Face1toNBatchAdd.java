package com.fubonlife.bio.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fubonlife.model.NBatchAdd;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1toNBatchAdd {

	Logger log = LoggerFactory.getLogger(Face1toNBatchAdd.class);
	Gson gson = new Gson();
	
	@Test
	public void test01() throws Exception {
		
	
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://223.22.252.52:803/group/batchadd";
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		File f1 = new File(System.getProperty("features"));

		FileSystemResource features = new FileSystemResource(f1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("groupname", System.getProperty("gname"));
		map.add("features", features);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);


				
	
		ResponseEntity<NBatchAdd> response = restTemplate.postForEntity(url, requestEntity,NBatchAdd.class);

		NBatchAdd o = response.getBody();
		
		
		
		log.info("============================================");
		log.info("輸入參數:groupname="+System.getProperty("gname"));
		log.info("輸入參數:features="+features);
		if(o.getFail_index() != null ) {
        for(Integer fail_index : o.getFail_index()){ 
        	log.info("fail_index: "+fail_index);
        } 
		} 
		if(o.getFail_reason() != null ) {
        for(Integer fail_reason : o.getFail_reason()){ 
        	log.info("fail_reason: "+fail_reason);
        }   
		}
		log.info("result: "+o.getResult());
		log.info("error: "+o.getError());
		log.info("============================================");


	}
	
}
