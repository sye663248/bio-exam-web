package com.fubonlife.bio.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fubonlife.model.NDelete;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1toNDelete {

	Logger log = LoggerFactory.getLogger(Face1toNDelete.class);
	
	@Test
	public void test01() throws Exception {
		
		String gname ="1NFaceDatabase";
		HttpHeaders httpHeaders= new HttpHeaders();
		
		
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("groupname", gname);
		map.add("featureid", 3);
		
		HttpEntity<MultiValueMap<String,Object>> requestEntity  = new HttpEntity<>(map,httpHeaders);
		
		RestTemplate restTemplate =	new RestTemplate();

		String url = "http://223.22.252.52:803/group/delete";
		ResponseEntity<NDelete> response = restTemplate.postForEntity(url, requestEntity,NDelete.class);
		
		NDelete o = response.getBody();
		
		log.info("============================================");
		log.info("result: "+o.getResult());
		log.info("error: "+o.getError());
		log.info("============================================");


	}
	
}
