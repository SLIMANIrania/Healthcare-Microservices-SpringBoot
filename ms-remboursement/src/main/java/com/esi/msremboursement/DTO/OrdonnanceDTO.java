package com.esi.msremboursement.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrdonnanceDTO {
    private LocalDate dateOrdonnance;
    private int nbMed;

}
