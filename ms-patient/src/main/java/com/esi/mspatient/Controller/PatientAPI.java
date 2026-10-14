package com.esi.mspatient.Controller;

import com.esi.mspatient.DTO.OrdonnanceDTO;
import com.esi.mspatient.DTO.RemboursementDTO;
import com.esi.mspatient.Entities.Patient;
import com.esi.mspatient.Repository.PatienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PatientAPI {
    final PatienRepository patientRepository ;
    final HttpGraphQlClient ordonnanceClient;
    final HttpGraphQlClient remboursementClient;

    public PatientAPI(PatienRepository patientRepository,
                      @Qualifier("OrdonnanceGraphQlClient")HttpGraphQlClient ordonnanceClient,
                      @Qualifier("RemboursementGraphQlClient")HttpGraphQlClient remboursementClient){
        this.patientRepository=patientRepository;
        this.ordonnanceClient = ordonnanceClient;
        this.remboursementClient = remboursementClient;
    }


    @GetMapping("patient/{idp}")
    public Patient getPatientWithOrdonannce(@PathVariable("idp") Long idPatient) {

        Patient patient=patientRepository.findById(idPatient).get();

        String query="{ getOrdonnanceByIdPatient(idPatient:"+idPatient+")"+
                "{ idOrdonnance medicaments {nom  cout dosage } }}";

        List<OrdonnanceDTO> ordonnances=ordonnanceClient.document(query)
                .retrieve("getOrdonnanceByIdPatient")
                .toEntityList(OrdonnanceDTO.class)
                .block();

        patient.setOrdonnances(ordonnances);

        if (ordonnances != null) {
            for (OrdonnanceDTO ord : ordonnances) {

                if (ord.getIdOrdonnance() == null) continue;

                String queryRem = "{ getRemboursementByOrdonnance(idOrdonnance:" + ord.getIdOrdonnance() + ")"
                        + "{ idRemboursement dateRemboursement montant idOrdonnance } }";

                RemboursementDTO remboursement = remboursementClient.document(queryRem)
                        .retrieve("getRemboursementByOrdonnance")
                        .toEntity(RemboursementDTO.class)
                        .block();

                ord.setRemboursement(remboursement);
            }
        }

        patient.setOrdonnances(ordonnances);

        return patient;

    }


}
