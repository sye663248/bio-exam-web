package com.fubonlife.bio.mg.service;

import javax.servlet.http.HttpSession;

public interface Face1to1Service {
//	login
	String login(String imgString, HttpSession session) throws Exception;
//	register
	String groupAdd(String imgString, String groupname) throws Exception;
	
	
	
//	1.1
	String verify(String imgString, String imgString2) throws Exception;
//	1.2
	String detect(String imgString) throws Exception;
//	1.3
	String extract(String imgString, String landmarks) throws Exception;
//	1.4
	String compare(String imgString1, String imgString2) throws Exception;
//	1.5
	String extract_with_detect(String imgString) throws Exception;
//	1.6
	String version();
}
