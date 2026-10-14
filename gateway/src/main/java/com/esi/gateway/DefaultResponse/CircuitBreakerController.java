package com.esi.gateway.DefaultResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CircuitBreakerController {

    @GetMapping("/defaultPatients")
    public List<Patient> PatientFallback(){
        List<Patient> Patients = new ArrayList<>();
        Patients.add(new Patient(1L,"said"));
        Patients.add(new Patient(2L,"roumaissa"));

        return Patients;
    }
}
