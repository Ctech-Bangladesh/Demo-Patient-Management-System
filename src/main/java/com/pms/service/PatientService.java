package com.pms.service;

import java.util.List;

import com.pms.patientDTO.PatientDTO;


public interface PatientService {

    public List<PatientDTO> getAllPatients();

    public PatientDTO addPatient(PatientDTO patientDTO);

    public void deletePatient(String id);

    public PatientDTO updatePatient(String id, PatientDTO patientUpdateDTO);

    public PatientDTO getPatient(String id);

}
