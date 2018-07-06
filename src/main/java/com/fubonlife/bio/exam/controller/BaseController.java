package com.fubonlife.bio.exam.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fubonlife.bio.exam.entity.mongo.BaseEntity;

@CrossOrigin(origins = "*")
public class BaseController {
	
	protected DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	protected DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	protected Pageable getPageable(HttpServletRequest request){
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));

		
		
		Sort sort = new Sort(Direction.ASC, "_id");
		
		Pageable pageable = PageRequest.of(page-1, limit, sort);
		return pageable;
	}
	
	protected Pageable getPageable(HttpServletRequest request, Sort sort){
		int limit = Integer.parseInt(request.getParameter("limit"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		Pageable pageable = PageRequest.of(page-1, limit, sort);
		
		return pageable;
	}
	
	protected void setCreateDateInfo(Object obj) {
		
		BaseEntity bn = (BaseEntity) obj;
		
		Date date = new Date();
		
		bn.setCreateDate(dayFormat.format(date));
		bn.setCreateTime(timeFormat.format(date));
		bn.setCreateTimestamp(date.getTime());
		bn.setCreateUid("A8303");
		
	}
	
	protected void setUpdateDateInfo(Object obj) {
		
		BaseEntity bn = (BaseEntity) obj;
		
		Date date = new Date();
		
		bn.setUpdateDate(dayFormat.format(date));
		bn.setUpdateTime(timeFormat.format(date));
		bn.setUpdateTimestamp(date.getTime());
		bn.setUpdateUid("A8303");
		
	}

}