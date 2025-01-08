package com.pms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.PatientDTO.PatientDTO;
import com.pms.entity.Patient;
import com.pms.repository.PatientRepositoryMongo;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements  PatientService{


	private final PatientRepositoryMongo patientRepository;

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
	public Patient updatePatient(String id, PatientDTO patientUpdateDTO) {
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
