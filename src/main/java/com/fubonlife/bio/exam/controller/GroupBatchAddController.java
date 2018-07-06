package com.fubonlife.bio.mg.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
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
public class GroupBatchAddController {

	Logger log = LoggerFactory.getLogger(GroupBatchAddController.class);
	@RequestMapping(value = "/groupbatchadd", method = RequestMethod.POST)
	public WebRestResponse groupbatchadd(@RequestParam("groupname") String gname
			,@RequestParam("features") MultipartFile features) throws IOException{
	
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		System.out.println("================>"+features.toString());
		
		File cfile = convert(features);
		
		FileSystemResource fileSystemResource =	new FileSystemResource(cfile);
		
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("groupname", gname);
		map.add("features", fileSystemResource);
		
		HttpEntity<MultiValueMap<String,Object>> requestEntity  = new HttpEntity<>(map,httpHeaders);
		
		RestTemplate restTemplate =	new RestTemplate();
		
		String url = "http://223.22.252.52:803/group/batchadd";

        	
       ResponseEntity<String> response  =	 restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        	
       String o = response.getBody();
				
       return WebRestResponse.success(o);

		
	}
	public File convert(MultipartFile file) throws IOException
	{    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
}
