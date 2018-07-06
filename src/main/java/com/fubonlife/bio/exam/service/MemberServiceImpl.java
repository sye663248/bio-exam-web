package com.fubonlife.bio.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fubonlife.bio.exam.entity.mongo.Member;
import com.fubonlife.bio.exam.repository.mongo.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
    MemberRepository memberRepository;

	@Override
	public Member create(Member obj) {
		
		Member o = new Member();
		
		o.setMemberId("A123456789");
		o.setName("TIM");
		
		memberRepository.save(o);
		
		return o;
	}

	@Override
	public Member update(Member obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member delete(Member obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Member> findByExample(Example example, Pageable pageable) {
		
		Page<Member> page = memberRepository.findAll(example, pageable);
		return page;
	}

	@Override
	public Page<Member> findAll(Pageable pageable) {
		Page<Member> page = memberRepository.findAll(pageable);
		return page;
	}

	@Override
	public List<Member> findAll() {
		List<Member> datas = memberRepository.findAll();
		return datas;
	}

	@Override
	public List<Member> findByName(String name) {
		List<Member> datas = memberRepository.findByName(name);
		return datas;
	}
	


}
