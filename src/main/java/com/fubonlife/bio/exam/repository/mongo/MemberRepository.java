package com.fubonlife.bio.exam.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fubonlife.bio.exam.entity.mongo.Member;


@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

	
	List<Member> findByName(String name);
	
	List<Member> findByNameAndAddress(String name, String address);
	
}

