package com.pms.service.impl;

import com.pms.entity.Patient;
import com.pms.patientDTO.PatientDTO;
import com.pms.repository.PatientRepositoryReactive;
import com.pms.service.PatientServiceReactive;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PatientServiceReactiveImpl implements PatientServiceReactive {

  private final PatientRepositoryReactive patientRepositoryReactive;
  private final ModelMapper modelMapper;

  @Override
  public Flux<PatientDTO> getAllPatients() {
    return patientRepositoryReactive
        .findAll()
        .map(patient -> modelMapper.map(patient, PatientDTO.class));
  }

  @Override
  public Mono<PatientDTO> addPatient(PatientDTO patientDTO) {
    Patient patient = modelMapper.map(patientDTO, Patient.class);
    return patientRepositoryReactive
        .save(patient)
        .map(savedPatient -> modelMapper.map(savedPatient, PatientDTO.class));
  }

  @Override
  public Mono<Void> deletePatient(String id) {
    return patientRepositoryReactive
        .findById(id)
        .switchIfEmpty(
            Mono.error(new NoSuchElementException("Patient with ID " + id + " not found.")))
        .flatMap(patient -> patientRepositoryReactive.deleteById(id));
  }

  @Override
  public Mono<PatientDTO> updatePatient(String id, PatientDTO patientDTO) {
    return patientRepositoryReactive
        .findById(id)
        .flatMap(
            existingPatient -> {
              existingPatient.setName(patientDTO.getName());
              existingPatient.setAge(patientDTO.getAge());
              return patientRepositoryReactive.save(
                  existingPatient);
            })
        .map(updatedPatient -> modelMapper.map(updatedPatient, PatientDTO.class))
        .switchIfEmpty(
            Mono.error(new NoSuchElementException("Patient with ID " + id + " not found.")));
  }

  @Override
  public Mono<PatientDTO> getPatient(String id) {
    return patientRepositoryReactive
        .findById(id)
        .map(patient -> modelMapper.map(patient, PatientDTO.class))
        .switchIfEmpty(
            Mono.error(new NoSuchElementException("Patient with ID " + id + " not found.")));
  }
}
