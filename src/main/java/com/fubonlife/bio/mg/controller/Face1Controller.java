package com.fubonlife.bio.mg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/face1to1")
public class Face1Controller {
	
	@RequestMapping("")
	public String faceLogin(Model model) {
		return "faceLogin";
	}
	
	@RequestMapping("/demoTest")
	public String demoTest(Model model, HttpSession session) {
		boolean isLogin = (boolean)((session.getAttribute("isLogin")==null)?false:session.getAttribute("isLogin")); 
		if(!isLogin) {
			System.out.println("沒登入");
			return "faceLogin";
		}
		return "demoTest";
	}
	
	@RequestMapping("/face2")
	public String face2(Model model) {
		return "face2";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
//	1.1
	@RequestMapping("/faceVerify")
	public String faceVerify(Model model) {
		return "faceVerify";
	}
	
//	1.2
	@RequestMapping("/faceDetect")
	public String faceDetect(Model model) {
		return "faceDetect";
	}
	
//	1.3
	@RequestMapping("/faceExtract")
	public String faceExtract(Model model) {
		return "faceExtract";
	}
	
//	1.4
	@RequestMapping("/faceCompare")
	public String faceCompare(Model model) {
		return "faceCompare";
	}
	
//	1.5
	@RequestMapping("/faceExtract_with_detect")
	public String faceExtract_with_detect(Model model){
		return "faceExtract_with_detect";
	}
	
//	1.6
	@RequestMapping("/faceVersion")
	public String faceVersion(Model model){
		return "faceVersion";
	}
}
