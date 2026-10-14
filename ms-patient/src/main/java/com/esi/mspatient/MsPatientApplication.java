package com.esi.mspatient;

import com.esi.mspatient.Entities.AntecedentMedical;
import com.esi.mspatient.Entities.Patient;
import com.esi.mspatient.Repository.AntecedentMedicalRepository;
import com.esi.mspatient.Repository.PatienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class MsPatientApplication implements CommandLineRunner {

    private final PatienRepository patientRepository;
    private final AntecedentMedicalRepository antecedentMedicalRepository;
    private final RepositoryRestConfiguration config;

    public static void main(String[] args) {
        SpringApplication.run(MsPatientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        config.exposeIdsFor(Patient.class, AntecedentMedical.class);

        Patient p1 = patientRepository.save(new Patient(null, "Rania", null,null,null));
        Patient p2 = patientRepository.save(new Patient(null, "Sarah", null,null,null));

        antecedentMedicalRepository.save(new AntecedentMedical(null, "Asthme", LocalDate.of(2020, 5, 12), p1));
        antecedentMedicalRepository.save(new AntecedentMedical(null, "Diabète", LocalDate.of(2021, 3, 20), p1));
        antecedentMedicalRepository.save(new AntecedentMedical(null, "Grippe", LocalDate.of(2019, 8, 10), p2));
    }
}
