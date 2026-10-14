package com.esi.msordonnance.Controller;

import com.esi.msordonnance.Entities.Ordonnance;
import com.esi.msordonnance.Proxy.PatientProxy;
import com.esi.msordonnance.Repository.OrdonnanceRepository;
import feign.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrdonnanceAPI {
    final OrdonnanceRepository ordonnanceRepository;
    final PatientProxy patientProxy;

    @GetMapping("ordonnace/{ido}")
    public Ordonnance getOrdonnance(@PathVariable("ido") Long ido) {
       Ordonnance ordonnance= ordonnanceRepository.findById(ido).get();
        ordonnance.setPatientDTO(patientProxy.getPatientDTO(ordonnance.getIdPatient()));
       return  ordonnance;
    }



}
