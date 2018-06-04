package com.fubonlife.bio.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fubonlife.model.Version;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1to1Version {

	Logger log = LoggerFactory.getLogger(Face1to1Version.class);

	@Test
	public void test01() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://223.22.252.52:802/version";
		ResponseEntity<Version> response = restTemplate.getForEntity(url, Version.class);
		
		Version o = response.getBody();
		
		log.info("============================================");
		log.info("version: "+o.getPlatformVersion());
		log.info("1e3: "+o.getThreshold().getLe3());
		log.info("1e4: "+o.getThreshold().getLe4());
		log.info("1e5: "+o.getThreshold().getLe5());
		log.info("1e6: "+o.getThreshold().getLe6());


	}

}
