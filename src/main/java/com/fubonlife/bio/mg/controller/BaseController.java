package com.fubonlife.bio.mg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class BaseController {
	
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

}
