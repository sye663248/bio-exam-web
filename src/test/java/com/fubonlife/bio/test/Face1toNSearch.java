package com.fubonlife.bio.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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

import com.fubonlife.model.NSearch;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1toNSearch {

	Logger log = LoggerFactory.getLogger(Face1toNSearch.class);
	Gson gson = new Gson();
	
	@Test
	public void test01() throws Exception {
		
		String gname ="1NFaceDatabase";
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://223.22.252.52:803/group/search";
		
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

//		File f1 = new File("D:\\feature.txt");
//		
//        InputStreamReader reader = new InputStreamReader(  
//                new FileInputStream(f1)); // 建立一个输入流对象reader  
//        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
//        String feature = "";  
//        feature = br.readLine();  
//        while (feature != null) {  
//        	System.out.println(feature);
//        	feature = br.readLine(); // 一次读入一行数据  
//        }  
		
		
		
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("groupname", gname);
		map.add("feature", "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");
		map.add("limit", 2);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);


				
	
		ResponseEntity<NSearch> response = restTemplate.postForEntity(url, requestEntity,NSearch.class);

		NSearch o = response.getBody();
		
		
		
		log.info("============================================");
		if(o.getIds() != null ) {
        for(Integer ids : o.getIds()){ 
        	log.info("ids: "+ids);
        } 
		} 
		if(o.getScores() != null ) {
        for(Double scores : o.getScores()){ 
        	log.info("scores: "+scores);
        }   
		}
		if(o.getTags() != null ) {
	        for(String tags : o.getTags()){ 
	        	log.info("tags: "+tags);
	        }   
			}
		log.info("result: "+o.getResult());
		log.info("error: "+o.getError());
		log.info("============================================");


	}
	
}
