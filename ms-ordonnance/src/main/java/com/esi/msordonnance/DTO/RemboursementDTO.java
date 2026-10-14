package com.esi.msordonnance.DTO;

public record RemboursementDTO(
        Long idOrdonnance,
        Double montant,
        String dateRemboursement,
        String numeroSecuriteSocial
) {}
