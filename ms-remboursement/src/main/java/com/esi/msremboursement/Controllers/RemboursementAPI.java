package com.esi.msremboursement.Controllers;

import com.esi.msremboursement.DTO.OrdonnanceDTO;
import com.esi.msremboursement.DTO.ResponseOrdonnanceDTO;
import com.esi.msremboursement.Entities.Remboursement;
import com.esi.msremboursement.Repository.RemboursementRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping ("api")
public class RemboursementAPI {
    final RemboursementRepository remboursementRepository;
    final HttpGraphQlClient ordonnanceClient;

    public RemboursementAPI(RemboursementRepository remboursementRepository,
                      @Qualifier("OrdonnanceGraphQlClient")HttpGraphQlClient ordonnanceClient){
        this.remboursementRepository= remboursementRepository;
        this.ordonnanceClient = ordonnanceClient;
    }

    @GetMapping("/remboursement/{id}")
    public Remboursement getRemboursementWithOrdonannce(@PathVariable("id") Long idRemboursement) {

        Remboursement remboursement = remboursementRepository.findById(idRemboursement)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Remboursement not found"));


        String queryOrdonnance = " query {" +
                "  getOrdonnanceByIdRemboursement(idRemboursement: " + idRemboursement + ") {" +
                "    dateOrdonnance" +
                "    medicaments {" +
                "      idMedicament" +
                "    }" +
                "  }" +
                "}";


        ResponseOrdonnanceDTO response = ordonnanceClient.document(queryOrdonnance)
                .retrieve("getOrdonnanceByIdRemboursement")
                .toEntity(ResponseOrdonnanceDTO.class)
                .block();
        if (response != null) {
            OrdonnanceDTO ordonnance = new OrdonnanceDTO();
            ordonnance.setDateOrdonnance(response.getDateOrdonnance());
            int count = response.getMedicaments() == null
                    ? 0
                    : response.getMedicaments().size();

            ordonnance.setNbMed(count);
            remboursement.setOrdonnance(ordonnance);
        }
        return remboursement;
    }
}


