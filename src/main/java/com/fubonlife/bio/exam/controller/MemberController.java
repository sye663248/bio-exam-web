package com.fubonlife.bio.exam.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fubonlife.bio.exam.entity.mongo.Member;
import com.fubonlife.bio.exam.service.MemberService;
import com.fubonlife.bio.exam.util.SmartExtCommand;
import com.fubonlife.bio.exam.util.WebRestResponse;


@RestController
@RequestMapping(value="/web/member")
public class MemberController extends BaseController{
	
	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
//	@RequestMapping(value = "/read2", method = RequestMethod.GET)
//    public WebRestResponse read2(HttpServletRequest request, String name) throws UnsupportedEncodingException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
//
//		log.info("name: "+name);
//
//        List<Member> datas = memberService.findByName(name);
//        return WebRestResponse.success(datas, datas.size());
//    }
	
	@RequestMapping(value = "/create1", method = RequestMethod.GET)
    public WebRestResponse create1(HttpServletRequest request) throws UnsupportedEncodingException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {


        Member o = memberService.create(null);
        return WebRestResponse.success(o);
    }
	
//	@RequestMapping(value = "/read1", method = RequestMethod.GET)
//    public WebRestResponse read1(HttpServletRequest request) throws UnsupportedEncodingException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
//
//
//        List<Member> datas = memberService.findAll();
//        return WebRestResponse.success(datas, datas.size());
//    }
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
    public WebRestResponse read(HttpServletRequest request) throws UnsupportedEncodingException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		System.out.println("request" + request);
		System.out.println("limit" + request.getParameter("limit"));
		System.out.println("page" + request.getParameter("page"));
		System.out.println("criteria" + request.getParameter("criteria"));
        if (request.getParameter("criteria") != null) {
        	SmartExtCommand command = new SmartExtCommand(request);
            Example<?> example = command.getExample(Member.class);
            Pageable pageable = getPageable(request);
            Page<?> pages = memberService.findByExample(example, pageable);
            return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
        }

        Pageable pageable = getPageable(request);
        Page<?> pages = memberService.findAll(pageable);
        return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
    }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebRestResponse create(@RequestBody Member obj) {

        //Job o = jobService.create(obj)
        //reloadScheduler()
        return WebRestResponse.success("OK");

    }
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
    public WebRestResponse update(@RequestBody Member obj) {
		//Member o = jobService.update(obj)
        //reloadScheduler()
        return WebRestResponse.success("OK");
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public WebRestResponse destroy(@RequestBody Member obj) {
        //Job o = jobService.delete(obj)
        //reloadScheduler()
        return WebRestResponse.success("OK");
    }
	
	
	
	/*
	
	@RequestMapping(value = '/read', method = RequestMethod.GET)
    public WebRestResponse read(HttpServletRequest request) {


        if (request.getParameter("criteria")) {
            SmartExtCommand command = new SmartExtCommand(request);
            Example<Job> example = command.getExample(Job.class)

            Pageable pageable = getPageable(request)
            Page<Job> pages = jobService.findByExample(example, pageable)

            return WebRestResponse.success(pages.content, pages.totalElements)
        }

        Pageable pageable = getPageable(request)
        Page<Job> pages = jobService.findAll(pageable)
        return WebRestResponse.success(pages.content, pages.totalElements)

    }
	
	@RequestMapping(value = '/create', method = RequestMethod.POST)
    public WebRestResponse create(@RequestBody Job obj) {

        Job o = jobService.create(obj)
        reloadScheduler()
        return WebRestResponse.success(o)

    }

    @RequestMapping(value = '/update', method = RequestMethod.PUT)
    public WebRestResponse update(@RequestBody Job obj) {
        Job o = jobService.update(obj)
        reloadScheduler()
        return WebRestResponse.success(o)
    }

    @RequestMapping(value = '/destroy', method = RequestMethod.DELETE)
    public WebRestResponse destroy(@RequestBody Job obj) {
        Job o = jobService.delete(obj)
        reloadScheduler()
        return WebRestResponse.success(o)
    }
    */
}
