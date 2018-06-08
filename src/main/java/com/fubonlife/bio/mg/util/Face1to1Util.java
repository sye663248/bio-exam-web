package com.fubonlife.bio.mg.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import sun.misc.BASE64Decoder;

public class Face1to1Util {

	public static String test(String url, MultiValueMap<String, Object> multiValueMap)
			throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multiValueMap, httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		// ResponseEntity<String> responseEntity =
		// restTemplate.postForEntity(url, null, String.class, requestEntity);
		String o = responseEntity.getBody();
		System.out.println("Response: " + o);
		return o;
	}

	public static String testJson(String url, String jsonString) throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				String.class);
		// ResponseEntity<String> responseEntity =
		// restTemplate.postForEntity(url, null, String.class, requestEntity);
		String o = responseEntity.getBody();
		System.out.println("Response: " + o);
		return o;
	}

	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	/**
	 * Base64字符串轉二進位制
	 *
	 * @param base64String
	 *            Base64
	 * @return base64String
	 * @throws IOException
	 *             異常
	 */
	public static byte[] getStringImage(String base64String) throws IOException {
		BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		return base64String != null ? decoder.decodeBuffer(base64String) : null;
	}



	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	
}