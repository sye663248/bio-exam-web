package com.fubonlife.bio.exam.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fubonlife.bio.exam.entity.mongo.Menu;
import com.fubonlife.bio.exam.util.WebRestResponse;

@RestController
@RequestMapping(value = "/web/main")
public class MainController extends BaseController{

	Logger log = LoggerFactory.getLogger(MainController.class);

	@ResponseBody
	@RequestMapping(value = "/readmenus", method = RequestMethod.POST)
	public WebRestResponse read(HttpServletRequest request) throws IOException {

		// Demo Menus
		List<Menu> menus = new ArrayList<Menu>();
		List<Menu> menuItems = new ArrayList<Menu>();
		Menu m1 = new Menu();

		m1.setMenuId("M1");
		m1.setText("欄位檢核");
		m1.setOrder(10);
		m1.setLeaf(false);
		System.out.println("userId " + request.getParameter("userId"));
		if ("admin".equals(request.getParameter("userId"))) {
			// 使用者 系統 表單 欄位 基本 特殊 關係
			Menu m11 = new Menu();

			m11.setMenuId("M11");
			m11.setText("使用者");
			m11.setView("MyApp.view.user.User");
			m11.setOrder(10);
			m11.setLeaf(true);

			Menu m12 = new Menu();

			m12.setMenuId("M12");
			m12.setText("系統");
			m12.setView("MyApp.view.system.System");
			m12.setOrder(10);
			m12.setLeaf(true);

			menuItems.add(m11);
			menuItems.add(m12);
		}

		Menu m13 = new Menu();

		m13.setMenuId("M13");
		m13.setText("表單");
		m13.setView("MyApp.view.form.Form");
		m13.setOrder(10);
		m13.setLeaf(true);

		Menu m14 = new Menu();

		m14.setMenuId("M14");
		m14.setText("欄位");
		m14.setView("MyApp.view.field.Field");
		m14.setOrder(10);
		m14.setLeaf(true);

		Menu m15 = new Menu();

		m15.setMenuId("M15");
		m15.setText("基本檢核");
		m15.setView("MyApp.view.basic.Basic");
		m15.setOrder(10);
		m15.setLeaf(true);

		Menu m16 = new Menu();

		m16.setMenuId("M16");
		m16.setText("特殊檢核");
		m16.setView("MyApp.view.special.Special");
		m16.setOrder(10);
		m16.setLeaf(true);

		Menu m17 = new Menu();

		m17.setMenuId("M17");
		m17.setText("關係檢核");
		m17.setView("MyApp.view.member.Member"); // Bio.web.ext.view.member.Member
		m17.setOrder(10);
		m17.setLeaf(true);

		menuItems.add(m13);
		menuItems.add(m14);
		menuItems.add(m15);
		menuItems.add(m16);
		menuItems.add(m17);

		m1.setData(menuItems);

		menus.add(m1);

		WebRestResponse response = WebRestResponse.success(menus, menus.size());

		return response;

	}

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebRestResponse create(HttpServletRequest request) throws IOException {

		log.info("== create ==");

		WebRestResponse response = WebRestResponse.success("OK");

		return response;

	}

	@ResponseBody
	@RequestMapping(value = "/readmenus2", method = RequestMethod.GET)
	public WebRestResponse read2(HttpServletRequest request) throws IOException {

		// Demo Menus
		List<Menu> menus = new ArrayList<Menu>();

		Menu m1 = new Menu();
		m1.setMenuId("M1");
		m1.setText("系統測試M1");
		m1.setOrder(10);
		m1.setLeaf(false);

		Menu m11 = new Menu();

		m11.setMenuId("M11");
		m11.setText("會員資料管理");
		m11.setView("Bio.web.ext.view.member.Member");
		m11.setOrder(10);
		m11.setLeaf(true);

		Menu m12 = new Menu();

		m12.setMenuId("M12");
		m12.setText("業務員查詢");
		m12.setView("Bio.web.ext.view.agent.Agent");
		m12.setOrder(10);
		m12.setLeaf(true);

		Menu m13 = new Menu();

		m13.setMenuId("M13");
		m13.setText("測試線上拍照");
		m13.setView("Bio.web.ext.view.testvideo.TestVideo");
		m13.setOrder(10);
		m13.setLeaf(true);

		Menu m14 = new Menu();

		m14.setMenuId("M14");
		m14.setText("測試線上拍照");
		m14.setView("Bio.web.ext.view.testvideocanvas.TestVideoCanvas");
		m14.setOrder(10);
		m14.setLeaf(true);

		Menu m15 = new Menu();

		m15.setMenuId("M15");
		m15.setText("人臉收集");
		m15.setView("Bio.web.ext.view.agentface.AgentFace");
		m15.setOrder(10);
		m15.setLeaf(true);

		List<Menu> menuItems = new ArrayList<Menu>();
		menuItems.add(m11);
		menuItems.add(m15);
		menuItems.add(m12);
		menuItems.add(m13);
		menuItems.add(m14);

		m1.setData(menuItems);

		menus.add(m1);

		WebRestResponse response = WebRestResponse.success(menus, menus.size());

		return response;

	}
}
