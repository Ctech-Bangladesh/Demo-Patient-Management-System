package com.pms.controller;

import com.pms.patientDTO.PatientDTO;
import com.pms.service.PatientServiceReactive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/api/patients")
public class PatientControllerReactive {
  private final PatientServiceReactive patientServiceReactive;

  @GetMapping
  public Flux<PatientDTO> getAllPatients() {
    return patientServiceReactive.getAllPatients();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<PatientDTO>> getPatient(@PathVariable("id") String id) {
    return patientServiceReactive
        .getPatient(id)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @PostMapping
  public Mono<ResponseEntity<PatientDTO>> addPatient(@RequestBody PatientDTO patientDTO) {
    return patientServiceReactive
        .addPatient(patientDTO)
        .map(savedPatient -> ResponseEntity.status(HttpStatus.CREATED).body(savedPatient));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<PatientDTO>> updatePatient(
      @PathVariable("id") String id, @RequestBody PatientDTO patientDTO) {
    return patientServiceReactive
        .updatePatient(id, patientDTO)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<String>> deletePatient(@PathVariable("id") String id) {
    return patientServiceReactive
        .deletePatient(id)
        .then(Mono.just(ResponseEntity.ok("Patient with ID " + id + " was successfully deleted.")));
  }
}
