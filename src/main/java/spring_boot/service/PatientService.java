package spring_boot.service;

import com.example.spring_boot.DTO.PatientDTO;
import com.example.spring_boot.entity.Patient;

import java.util.List;

public interface PatientService {
    public List<PatientDTO> getPatients();
    public Patient getPatientById(String id);
    public Patient addPatient(Patient patient);
    public Patient updatePatient(String id, Patient patient);
    public void deletePatient(String id);
}
