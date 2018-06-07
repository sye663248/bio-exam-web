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

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1to1Compare {

	Logger log = LoggerFactory.getLogger(Face1to1Verify.class);

	Gson gson = new Gson();

	@Test
	public void test01() throws Exception {

		String url = "http://223.22.252.52:802/compare";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		File f1 = new File("src/main/resources/images/kairos-elizabeth.jpg");

		FileSystemResource v1 = new FileSystemResource(f1);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("image", v1);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

		String o = response.getBody();

		log.info("============================================");
		log.info("Response: " +o);

	}

}
