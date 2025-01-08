package com.pms.service;

import java.util.List;

import com.pms.PatientDTO.PatientDTO;
import com.pms.entity.Patient;


public interface PatientService {
	
	public List<Patient> getAllPatients();

	public Patient addPatient(Patient patient);

	public void deletePatient(String id);

	public Patient updatePatient(String id, PatientDTO patientUpdateDTO);

	public Patient getPatient(String id);

}
