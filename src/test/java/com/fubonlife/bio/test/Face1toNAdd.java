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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fubonlife.model.NAdd;

@RunWith(SpringJUnit4ClassRunner.class)
public class Face1toNAdd {

	Logger log = LoggerFactory.getLogger(Face1toNAdd.class);
	
	@Test
	public void test01() throws Exception {
		
		
		
		HttpHeaders httpHeaders= new HttpHeaders();
		
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
		
		
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("groupname", System.getProperty("gname"));
		map.add("featureid", System.getProperty("featureid"));
		map.add("feature", feature);
		map.add("tag", System.getProperty("tag"));
		
		HttpEntity<MultiValueMap<String,Object>> requestEntity  = new HttpEntity<>(map,httpHeaders);
		
		RestTemplate restTemplate =	new RestTemplate();

		String url = "http://223.22.252.52:803/group/add";
		ResponseEntity<NAdd> response = restTemplate.postForEntity(url, requestEntity,NAdd.class);
		
		NAdd o = response.getBody();
		
		log.info("============================================");
		log.info("輸入參數:groupname="+System.getProperty("gname"));
		log.info("輸入參數:featureid="+System.getProperty("featureid"));
		log.info("輸入參數:feature="+feature);
		log.info("輸入參數:tag="+System.getProperty("tag"));
		log.info("result: "+o.getResult());
		log.info("error: "+o.getError());
		log.info("============================================");


	}
	
}
