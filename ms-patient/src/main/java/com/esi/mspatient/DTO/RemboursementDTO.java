package com.esi.mspatient.DTO;

public record RemboursementDTO(

       Long idRemboursement,
       String dateRemboursement,
        Double montant,
       Long idOrdonnance
) {
}
