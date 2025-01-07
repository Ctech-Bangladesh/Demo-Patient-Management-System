package com.pms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pms.PatientDTO.PatientUpdateDTO;
import com.pms.entity.Patient;
import com.pms.service.PatientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.addPatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") String id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient with ID " + id + " was successfully deleted.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") String id,
                                                 @RequestBody PatientUpdateDTO patientUpdateDTO) {
        Patient updatedPatient = patientService.updatePatient(id, patientUpdateDTO);
        return ResponseEntity.ok(updatedPatient);
    }
}
