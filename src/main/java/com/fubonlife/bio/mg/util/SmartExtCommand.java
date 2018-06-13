package com.fubonlife.bio.mg.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.google.gson.Gson;



public class SmartExtCommand {
	
	int page;
	int start;
	int limit;

	List<SmartExtCriteria> extCriterias = new ArrayList<SmartExtCriteria>();
	
	Pageable pageable;
	
	public SmartExtCommand(HttpServletRequest request) throws UnsupportedEncodingException{
		try {
			this.page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
		}

		try {
			this.start = Integer.parseInt(request.getParameter("start"));
		} catch(Exception e) {
		}

		try {
			this.limit = Integer.parseInt(request.getParameter("limit"));
		} catch(Exception e) {
		}
		
		Sort sort = new Sort(Direction.ASC, "_id");
		this.pageable = PageRequest.of(page-1, limit, sort);

		String[] criteriaStrs = request.getParameterValues("criteria");

		Gson gson = new Gson();
		if (criteriaStrs != null && criteriaStrs.length > 0) {
			for (String criteriaStr : criteriaStrs) {
				String utf8Str = new String(criteriaStr.getBytes("ISO-8859-1"), "utf-8");
				SmartExtCriteria smartGwtCriteria = gson.fromJson(utf8Str, SmartExtCriteria.class);
				extCriterias.add(smartGwtCriteria);
			}
		}
	}
	
	public void addExtCriteria(String field, Object value) {
		
		SmartExtCriteria criteria = new SmartExtCriteria();
		criteria.setField(field);
		criteria.setValue(value);
		
		extCriterias.add(criteria);
	}
	
	public Example<?> getExample(Class<?> clazz) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		Object o = clazz.newInstance();
		ExampleMatcher matcher = ExampleMatcher.matching();
		List<String> list = new ArrayList<String>();
		
		// 只指定查詢相關欄位 其餘忽略
		for(SmartExtCriteria criteria : extCriterias){
			list.add(criteria.getField());
		}
		for(Field field : clazz.getDeclaredFields()){
			if(list.contains(field.getName())) 
				continue;
			else 
				matcher = matcher.withIgnorePaths(field.getName());
		}
		
		// 設定查詢欄位查詢方式都為『包含』
		Class<?> c = o.getClass();
		for(SmartExtCriteria criteria: extCriterias) {
			
			Field f = c.getDeclaredField(criteria.getField());
			f.setAccessible(true);
			f.set(o, criteria.getValue());
			matcher = matcher.withMatcher(criteria.getField(), ExampleMatcher.GenericPropertyMatchers.contains());
			
		}
		
		return  Example.of(o, matcher);
		
	}
	
	

}
