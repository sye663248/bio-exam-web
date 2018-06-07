package com.fubonlife.bio.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		
		
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://223.22.252.52:803/group/search";
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		File f1 = new File(System.getProperty("feature"));
		
        InputStreamReader reader = new InputStreamReader(  
                new FileInputStream(f1)); // 建立一個輸入流對象reader  
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(reader); // 建立一個對象，它把文件內容轉成計算機能讀懂的語言 
        String feature = "";
        feature = br.readLine();  
        while (br.readLine() != null) {  
        	
        	feature += br.readLine(); // 一次讀入一行數據 
        	
        	
        }  
				
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("groupname", System.getProperty("gname"));
		map.add("feature", feature);
		map.add("limit", System.getProperty("limit"));
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);


				
	
		ResponseEntity<NSearch> response = restTemplate.postForEntity(url, requestEntity,NSearch.class);

		NSearch o = response.getBody();
		
		
		
		log.info("============================================");
		log.info("輸入參數:groupname="+System.getProperty("gname"));
		log.info("輸入參數:feature="+feature);
		log.info("輸入參數:limit="+System.getProperty("limit"));	
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
