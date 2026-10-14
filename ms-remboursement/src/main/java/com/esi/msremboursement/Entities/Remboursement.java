package com.esi.msremboursement.Entities;

import com.esi.msremboursement.DTO.OrdonnanceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remboursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRemboursement;
    private LocalDate dateRemboursement;
    private Double montant;
    @ManyToOne
    private Assure assure;
    private Long idOrdonnance;


    @Transient
    private OrdonnanceDTO ordonnance;


}
