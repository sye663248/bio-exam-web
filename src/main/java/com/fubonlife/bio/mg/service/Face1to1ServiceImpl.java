package com.fubonlife.bio.mg.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.mg.controller.GroupAddController;
import com.fubonlife.bio.mg.controller.GroupGetSizeController;
import com.fubonlife.bio.mg.controller.GroupSearchController;
import com.fubonlife.bio.mg.util.Face1to1Util;

public class Face1to1ServiceImpl implements Face1to1Service {

//	@Override
//	public String login(String imgString, HttpSession session) throws Exception {
//		String url = "http://223.22.252.52:802/verify";
//		byte[] buff = Face1to1Util.getStringImage(imgString.substring(imgString.indexOf(",") + 1));
//		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
//		FileOutputStream fos = new FileOutputStream(new File("src/main/resources/images/Test1.png"));
//		fos.write(buff);
//		fos.close();
//		Thread.sleep(400);
//		multiValueMap.add("image_best", new ClassPathResource("images/Test1.png"));
//		multiValueMap.add("image_idcard", new ClassPathResource("images/Test0.png"));
//		// multiValueMap.add("image_idcard", new
//		// ClassPathResource("images/Test2.png"));
//		// 證件處理網紋 是1 否0 預設0
//		// multiValueMap.add("watermark", 1);
//		// 圖片安全驗證 SDK return
//		// multiValueMap.add("delta", "delta");
//		// 偵測不到時旋轉 0~4 預設0(兩張都轉)
//		// multiValueMap.add("rotate", 0);
//		session.setAttribute("isLogin", true);
//		return Face1to1Util.test(url, multiValueMap);
//	}
	
	@Override
	public String login(String imgString, HttpSession session) throws Exception {
		String val = extract_with_detect(imgString);
		String feature = val.substring(val.indexOf("Feature")+10,val.length()-5);
		GroupSearchController groupSearchController = new GroupSearchController();
//		group1 YSY ...
		String result = groupSearchController.groupsearch("test",feature).getData().toString();
		System.out.println("result " + result );
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// convert JSON string to Map
		map = mapper.readValue(result, new TypeReference<Map<String, Object>>(){});
		List<Double> noScore = new ArrayList<Double>();
		noScore.add(0.1);
		@SuppressWarnings("unchecked")
		List<Double> scores = (ArrayList<Double>)((map.get("scores")==null)?noScore:map.get("scores"));
		if(scores.get(0)>79.9) {
			session.setAttribute("isLogin", true);
		} else {
			session.setAttribute("isLogin", false);
		}
		return result;
	}
	
	@Override
	public String groupAdd(String imgString, String groupname) throws Exception {
		// TODO Auto-generated method stub
		String val = extract_with_detect(imgString);
		String feature = val.substring(val.indexOf("Feature")+10,val.length()-5);
//		1.7
		GroupGetSizeController groupGetSizeController = new GroupGetSizeController();
		String data = groupGetSizeController.groupgetsize(groupname).getData().toString();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// convert JSON string to Map
		map = mapper.readValue(data, new TypeReference<Map<String, Object>>(){});
		Integer groupSize = (Integer)((map.get("size")==null)?0:map.get("size"));
		Integer featureid = 1;
		if(groupSize != 0 ){
			featureid = groupSize + 1; 
		}
		GroupAddController groupAddController = new GroupAddController();
		String result = groupAddController.groupadd(groupname, featureid.toString(), feature).getData().toString();
		
		return result;
	}

	@Override
	public String verify(String imgString, String imgString2) throws Exception{
		String url = "http://223.22.252.52:802/verify";
		byte[] buff = Face1to1Util.getStringImage(imgString.substring(imgString.indexOf(",") + 1));
		byte[] buff2 = Face1to1Util.getStringImage(imgString2.substring(imgString2.indexOf(",") + 1));
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		FileOutputStream fos = new FileOutputStream(new File("src/main/resources/images/Test1.png")); 
		fos.write(buff); 
		fos.close();
		FileOutputStream fos2 = new FileOutputStream(new File("src/main/resources/images/Test2.png")); 
		fos2.write(buff2); 
		fos2.close();
		Thread.sleep(400);
		multiValueMap.add("image_best", new ClassPathResource("images/Test1.png"));
		multiValueMap.add("image_idcard", new ClassPathResource("images/Test2.png"));
		//證件處理網紋 是1 否0 預設0
//		multiValueMap.add("watermark", 1);
		//圖片安全驗證 SDK return
//		multiValueMap.add("delta", "delta");
		//偵測不到時旋轉 0~4 預設0(兩張都轉)
//		multiValueMap.add("rotate", 0);
		
		return Face1to1Util.test(url, multiValueMap);
	}

	@Override
	public String detect(String imgString) throws Exception {
		String url = "http://223.22.252.52:802/detect";
		byte[] buff = Face1to1Util.getStringImage(imgString.substring(imgString.indexOf(",") + 1));
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		FileOutputStream fos = new FileOutputStream(new File("src/main/resources/images/Test1.png")); 
		fos.write(buff); 
		fos.close();
		Thread.sleep(400);
		ClassPathResource v1 = new ClassPathResource("images/Test1.png");
		multiValueMap.add("image", v1);
		//證件處理網紋 是1 否0 預設0
//		multiValueMap.add("watermark", 1);
		//圖片安全驗證 SDK return
//		multiValueMap.add("delta", "delta");
		//偵測不到時旋轉 0~4 預設0(兩張都轉)
//		multiValueMap.add("rotate", 0);
		//最大的臉
//		multiValueMap.add("maxface", 1);
		//切特寫臉照片
//		multiValueMap.add("cutimg", 1);
		
		return Face1to1Util.test(url, multiValueMap);
	}

	@Override
	public String extract(String imgString, String landmarks) throws Exception {
		String url = "http://223.22.252.52:802/extract";
		byte[] buff = Face1to1Util.getStringImage(imgString.substring(imgString.indexOf(",") + 1));
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		FileOutputStream fos = new FileOutputStream(new File("src/main/resources/images/Test1.png")); 
		fos.write(buff); 
		fos.close();
		Thread.sleep(400);
		ClassPathResource v1 = new ClassPathResource("images/Test1.png");
		multiValueMap.add("landmarks", landmarks);
		multiValueMap.add("image", v1);	
		//證件處理網紋 是1 否0 預設0
//		multiValueMap.add("watermark", 1);
		//圖片安全驗證 SDK return
//		multiValueMap.add("delta", "delta");
		HashMap<String, String> map = new HashMap<>();
		HashMap<String, byte[]> byteMap = new HashMap<>();
		map.put("landmarks", landmarks);
		return Face1to1Util.test(url, multiValueMap);
	}

	@Override
	public String compare(String imgString1, String imgString2) throws Exception {
		String extract_with_detect1 = extract_with_detect(imgString1);
		String test1 = extract_with_detect1.substring(extract_with_detect1.indexOf("Feature"));
		String feature1 = test1.substring(test1.indexOf("Feature")+10,test1.length()-5);
		System.out.println("feature1 " + feature1);
		String extract_with_detect2 = extract_with_detect(imgString2);
		String test2 = extract_with_detect2.substring(extract_with_detect2.indexOf("Feature"));
		String feature2 = test2.substring(test2.indexOf("Feature")+10,test2.length()-5);
		System.out.println("feature2 " + feature2);
		String url = "http://223.22.252.52:802/compare";
		Map<String,String> featMap = new HashMap<String,String>();
		String featJSONString = "";
		featMap.put("feat1", feature1);
		featMap.put("feat2", feature2);
		featJSONString = new ObjectMapper().writeValueAsString(featMap);
		System.out.println(featJSONString);
		return Face1to1Util.testJson(url, featJSONString);
	}

	@Override
	public String extract_with_detect(String imgString) throws Exception {
		String url = "http://223.22.252.52:802/extract_with_detect";
		byte[] buff = Face1to1Util.getStringImage(imgString.substring(imgString.indexOf(",") + 1));
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		FileOutputStream fos = new FileOutputStream(new File("src/main/resources/images/Test1.png")); 
		fos.write(buff); 
		fos.close();
		Thread.sleep(400);
		ClassPathResource v1 = new ClassPathResource("images/Test1.png");
		multiValueMap.add("image", v1);
		//證件處理網紋 是1 否0 預設0
//		multiValueMap.add("watermark", 1);
		//圖片安全驗證 SDK return
//		multiValueMap.add("delta", "delta");
		//偵測不到時旋轉 0~4 預設0(兩張都轉)
//		multiValueMap.add("rotate", 0);
		//最大的臉
//		multiValueMap.add("maxface", 1);
		//切特寫臉照片
//		multiValueMap.add("cutimg", 1);
		
		return Face1to1Util.test(url, multiValueMap);
	}

	@Override
	public String version() {
		String url = "http://223.22.252.52:802/version";
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<String, Object>();
		String str =null;
     	try {
     		HttpHeaders httpHeaders = new HttpHeaders();
    		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
    		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multiValueMap, httpHeaders);
    		RestTemplate restTemplate = new RestTemplate();
    		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
    		str = responseEntity.getBody();
//    		ResponseEntity<Version> response = restTemplate.getForEntity(url, Version.class);
//    		Version version = response.getBody();
    		System.out.println("Response: " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	
	
}
