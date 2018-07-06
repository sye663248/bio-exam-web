package com.fubonlife.bio.mg.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubonlife.bio.mg.entity.mongo.Exams;
import com.fubonlife.bio.mg.service.ExamsService;
import com.fubonlife.bio.mg.util.ExamUtil;
import com.fubonlife.bio.mg.util.SmartExtCommand;
import com.fubonlife.bio.mg.util.WebRestResponse;

@RestController
@RequestMapping(value="/web/exam")
public class ExamsController extends BaseController{
	
	@Autowired
	private ExamsService examsService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/create", method = RequestMethod.POST)	
	public WebRestResponse create(@RequestBody String data) throws Exception {
		System.out.println("insert Exam " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = objectMapper.readValue(data, Map.class);
		Exams exam = objectMapper.readValue(data, Exams.class);
		String lastId = "";
		Exams lastExam = examsService.findTopByOrderByExamIdDesc();
		if (lastExam != null) {
			lastId = lastExam.getExamId();
		}
		map = new ExamUtil().setId(lastId, map, "exam");
		exam.setExamId(map.get("examId"));
		examsService.create(exam);
		return WebRestResponse.success(exam,1);
	}
	
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public WebRestResponse read1() throws Exception {
		List<Exams> exams = examsService.readAll();
		return WebRestResponse.success(exams,exams.size());
	}
	
	@RequestMapping(value = "/read2", method = RequestMethod.GET)
	public WebRestResponse read(HttpServletRequest request) throws Exception {
		System.out.println("limit" + request.getParameter("limit"));
		if (request.getParameter("criteria") != null) {
    		SmartExtCommand command = new SmartExtCommand(request);
	        Example<?> example = command.getExample(Exams.class);
	        Pageable pageable = getPageable(request);
	        Page<Exams> pages = examsService.findByExample(example, pageable);
	        return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
		}
	    Pageable pageable = getPageable(request);
	    Page<Exams> pages = examsService.findAll(pageable);
	    return WebRestResponse.success(pages.getContent(), pages.getTotalElements());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readById", method = RequestMethod.POST)
	public Exams readById(@RequestBody String examId) throws Exception {
		System.out.println(examId);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap map = objectMapper.readValue(examId, HashMap.class);
		examId = (String) map.get("examId");
		System.out.println(examId);
		return examsService.read(examId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public WebRestResponse update(@RequestBody String data) throws Exception {
		System.out.println("modify System " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String,String> map = objectMapper.readValue(data, HashMap.class);
		Exams exam = examsService.read(map.get("examId"));
		exam.setExamItem(map.get("examItem"));
		exam.setExamName(map.get("examName"));
		exam.setExamType(map.get("examType"));
		examsService.update(exam);
		return WebRestResponse.success(exam);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public WebRestResponse delete(@RequestBody String data) throws Exception {
		System.out.println("class "+data.getClass());
		System.out.println("delete Field " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		Exams exam = objectMapper.readValue(data, Exams.class);
		examsService.delete(exam.getExamId());
		return WebRestResponse.success(exam,1);
	}
	
	
}
