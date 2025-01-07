package com.pms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.entity.Patient;

@Repository
public interface PatientRepositoryMongo extends MongoRepository<Patient, String>{
}
