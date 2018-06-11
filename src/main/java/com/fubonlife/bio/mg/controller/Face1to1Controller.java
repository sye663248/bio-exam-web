package com.fubonlife.bio.mg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fubonlife.bio.mg.service.Face1to1Service;
import com.fubonlife.bio.mg.service.Face1to1ServiceImpl;

@Controller
@RequestMapping(value = "/face1to1")
public class Face1to1Controller {

	// login
	@RequestMapping("/login")
	@ResponseBody
	public String login(String imgString, HttpSession session) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.login(imgString, session);
	}

	// login
	@RequestMapping("/groupAdd")
	@ResponseBody
	public String groupAdd(String imgString, String groupname) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.groupAdd(imgString, groupname);
	}

	// 1.1
	@RequestMapping("/verify")
	@ResponseBody
	public String verify(String imgString, String imgString2) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.verify(imgString, imgString2);
	}

	// 1.2
	@RequestMapping(value = "/detect")
	@ResponseBody
	public String detect(String imgString) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.detect(imgString);
	}

	// 1.3
	@RequestMapping("/extract")
	@ResponseBody
	public String extract(String imgString, String landmarks) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.extract(imgString, landmarks);
	}

	// 1.4
	@RequestMapping("/compare")
	@ResponseBody
	public String compare(String imgString1, String imgString2) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.compare(imgString1, imgString2);
	}

	// 1.5
	@RequestMapping("/extract_with_detect")
	@ResponseBody
	public String extract_with_detect(String imgString) throws Exception {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.extract_with_detect(imgString);
	}

	// 1.6
	@RequestMapping("/version")
	@ResponseBody
	public String version() {
		Face1to1Service face1to1Service = new Face1to1ServiceImpl();
		return face1to1Service.version();
	}

}
