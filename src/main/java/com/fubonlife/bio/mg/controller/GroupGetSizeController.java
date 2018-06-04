package com.fubonlife.bio.mg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fubonlife.bio.mg.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/api")
public class GroupGetSizeController {

	Logger log = LoggerFactory.getLogger(GroupGetSizeController.class);
	@RequestMapping(value = "/groupgetsize", method = RequestMethod.POST)
	public WebRestResponse groupgetsize(@RequestParam("groupname") String gname){
	
		HttpHeaders httpHeaders= new HttpHeaders();
		
		
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("groupname", gname);
		
		HttpEntity<MultiValueMap<String,Object>> requestEntity  = new HttpEntity<>(map,httpHeaders);
		
		RestTemplate restTemplate =	new RestTemplate();
		
		String url = "http://223.22.252.52:803/group/getsize";

        	
       ResponseEntity<String> response  =	 restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        	
       String o = response.getBody();
				
       return WebRestResponse.success(o);
		
		
		
		
		
		
		
		
	}
}
