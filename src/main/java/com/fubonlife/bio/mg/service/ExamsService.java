package com.fubonlife.bio.mg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fubonlife.bio.mg.entity.mongo.Exams;

public interface ExamsService {
	public List<Exams> readAll();
	public Exams read(String examId);
	public Exams create(Exams exam);
	public Exams update(Exams exam);
	public String delete(String examId);
	public Page<Exams> findByExample(Example example, Pageable pageable);
	public Page<Exams> findAll(Pageable pageable);
	public List<Exams> findAll();
	public Exams findTopByOrderByExamIdDesc();
}
