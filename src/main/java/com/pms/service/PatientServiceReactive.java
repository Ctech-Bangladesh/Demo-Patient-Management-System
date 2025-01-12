package com.pms.service;

import com.pms.patientDTO.PatientDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientServiceReactive {
  public Flux<PatientDTO> getAllPatients();

  public Mono<PatientDTO> addPatient(PatientDTO patientDTO);

  public Mono<Void> deletePatient(String id);

  public Mono<PatientDTO> updatePatient(String id, PatientDTO patientDTO);

  public Mono<PatientDTO> getPatient(String id);
}
