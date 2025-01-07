package com.pms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.PatientDTO.PatientUpdateDTO;
import com.pms.entity.Patient;
//import com.pms.repository.PatientRepository;
import com.pms.repository.PatientRepositoryMongo;

//import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientServiceImp implements  PatientService{

	@Autowired
	private PatientRepositoryMongo patientRepository;

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	@Override
	public void deletePatient(String id) {
		Optional<Patient> patient = patientRepository.findById(id);

		if (!patient.isPresent()) {
			throw new NoSuchElementException ("Patient with ID " + id + " not found.");
		}
		patientRepository.deleteById(id);
	}
	
	@Override
	public Patient updatePatient(String id, PatientUpdateDTO patientUpdateDTO) {
		Optional<Patient> patientOpt = patientRepository.findById(id);
		if (!patientOpt.isPresent()) {
			throw new NoSuchElementException ("Patient with ID " + id + " not found.");
		}

		Patient patient = patientOpt.get();
		patient.setName(patientUpdateDTO.getName());
		patient.setAge(patientUpdateDTO.getAge());

		return patientRepository.save(patient);
	}

	@Override
	public Patient getPatient(String id) {
		Optional<Patient> patient = patientRepository.findById(id);

		if (!patient.isPresent()) {
			throw new NoSuchElementException ("Patient with ID " + id + " not found.");
		}
		return patient.get();
	}

}
