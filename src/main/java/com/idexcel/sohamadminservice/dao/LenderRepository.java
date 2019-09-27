package com.idexcel.sohamadminservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.idexcel.sohamadminservice.entity.Lender;

@Repository
public interface LenderRepository extends MongoRepository<Lender,String>{
	@Query("{id : ?0}")
	public Lender findByid(String lenderId);
	@Query("{name : ?0}")
	public Lender findByName(String name);

}
