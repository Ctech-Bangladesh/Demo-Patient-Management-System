package com.pms.controller;

import com.pms.patientDTO.PatientDTO;
import com.pms.service.PatientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/patients")
public class PatientController {

  private final PatientService patientService;

  @GetMapping
  public ResponseEntity<List<PatientDTO>> getAllPatients() {
    return ResponseEntity.ok(patientService.getAllPatients());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") String id) {
    return ResponseEntity.ok(patientService.getPatient(id));
  }

  @PostMapping
  public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) {
    return ResponseEntity.ok(patientService.addPatient(patientDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePatient(@PathVariable("id") String id) {
    patientService.deletePatient(id);
    return ResponseEntity.ok("Patient with ID " + id + " was successfully deleted.");
  }

  @PutMapping("/{id}")
  public ResponseEntity<PatientDTO> updatePatient(
      @PathVariable("id") String id, @RequestBody PatientDTO patientDTO) {

    return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
  }
}
