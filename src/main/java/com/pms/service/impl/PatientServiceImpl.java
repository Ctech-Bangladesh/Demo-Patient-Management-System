package com.pms.service.impl;

import com.pms.patientDTO.PatientDTO;
import com.pms.entity.Patient;
import com.pms.repository.PatientRepository;
import com.pms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {

        Patient patient = modelMapper.map(patientDTO, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
    }


    @Override
    public void deletePatient(String id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isEmpty()) {
            throw new NoSuchElementException("Patient with ID " + id + " not found.");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDTO updatePatient(String id, PatientDTO patientUpdateDTO) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new NoSuchElementException("Patient with ID " + id + " not found.");
        }

        Patient patient = patientOpt.get();
        patient.setName(patientUpdateDTO.getName());
        patient.setAge(patientUpdateDTO.getAge());

        return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
    }

    @Override
    public PatientDTO getPatient(String id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isEmpty()) {
            throw new NoSuchElementException("Patient with ID " + id + " not found.");
        }
        modelMapper.map(patient.get(), PatientDTO.class);
        return modelMapper.map(patient.get(), PatientDTO.class);
    }


}
