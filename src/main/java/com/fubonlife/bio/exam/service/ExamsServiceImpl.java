package com.fubonlife.bio.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.exam.entity.mongo.Exams;
import com.fubonlife.bio.exam.repository.mongo.ExamsRepository;

@Service
public class ExamsServiceImpl implements ExamsService{

	@Autowired
	ExamsRepository examsRepository;
	
	@Override
	public List<Exams> readAll() {
		return examsRepository.findAll();
	}

	@Override
	public Exams read(String examId) {
		return examsRepository.findById(examId).get();
	}

	@Override
	public Exams create(Exams exam) {
		return examsRepository.insert(exam);
	}

	@Override
	public Exams update(Exams exam) {
		return examsRepository.save(exam);
	}

	@Override
	public String delete(String examId) {
		examsRepository.deleteById(examId);
		return examId;
	}

	@Override
	public Page<Exams> findByExample(Example example, Pageable pageable) {
		return examsRepository.findAll(example, pageable);
	}

	@Override
	public Page<Exams> findAll(Pageable pageable) {
		return examsRepository.findAll(pageable);
	}

	@Override
	public List<Exams> findAll() {
		return examsRepository.findAll();
	}

	@Override
	public Exams findTopByOrderByExamIdDesc() {
		return examsRepository.findTopByOrderByExamIdDesc();
	}

}
