package com.fubonlife.bio.mg.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fubonlife.bio.mg.entity.mongo.Form;


@Repository
public interface FormRespository extends MongoRepository<Form, String> {

}
