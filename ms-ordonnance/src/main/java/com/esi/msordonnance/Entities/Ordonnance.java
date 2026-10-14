package com.esi.msordonnance.Entities;

import com.esi.msordonnance.DTO.PatientDTO;
import com.esi.msordonnance.DTO.RemboursementDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdonnance;

    private LocalDate dateOrdonnance;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(
            mappedBy = "ordonnance",
            cascade = CascadeType.ALL,
            fetch = FetchType
                    .EAGER
    )
    private List<Medicament> medicaments;


    private Long idPatient;

    @Transient
    private PatientDTO patientDTO;

    @Transient
    private RemboursementDTO remboursement;


}
