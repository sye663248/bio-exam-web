package com.fubonlife.bio.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fubonlife.model.Verify;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1to1Verify {

	Logger log = LoggerFactory.getLogger(Face1to1Verify.class);
	
	Gson gson = new Gson();

	@Test
	public void test01() throws Exception {
		
		String url = "http://223.22.252.52:802/verify";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		File f1 = new File("src/main/resources/images/kairos-elizabeth.jpg");
		File f2 = new File("src/main/resources/images/kairos-elizabeth.jpg");
		
		FileSystemResource v1 = new FileSystemResource(f1);
		FileSystemResource v2 = new FileSystemResource(f2);
		
		
		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		map.add("image_best", v1);
		map.add("image_idcard", v2);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Verify> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Verify.class);
       
		Verify o = response.getBody();
		
		log.info("============================================");
		log.info("Response: "+gson.toJson(o));
	}
	
	@Test
	public void test02() throws Exception {
		
		String url = "http://223.22.252.52:802/verify";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		File f1 = new File("src/main/resources/images/kairos-elizabeth.jpg");
		File f2 = new File("src/main/resources/images/kairos-elizabeth-2.jpg");
		
		FileSystemResource v1 = new FileSystemResource(f1);
		FileSystemResource v2 = new FileSystemResource(f2);
		
		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		map.add("image_best", v1);
		map.add("image_idcard", v2);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Verify> response = restTemplate.postForEntity(url, requestEntity, Verify.class);
		
		Verify o = response.getBody();
		
		log.info("============================================");
		log.info("Response: "+gson.toJson(o));
	}

}
