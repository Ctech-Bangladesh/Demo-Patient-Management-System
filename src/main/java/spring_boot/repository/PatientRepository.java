package spring_boot.repository;

import com.example.spring_boot.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findAllByName(String name);
    Patient findByEmail(String email);
}
