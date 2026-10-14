package com.esi.msremboursement.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseOrdonnanceDTO {
    private LocalDate dateOrdonnance;
    private List<MedicamentDTO> medicaments;
}
