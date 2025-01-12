package com.pms.repository;

import com.pms.entity.Patient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepositoryReactive extends ReactiveMongoRepository<Patient, String> {}
