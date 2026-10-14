package com.esi.mspatient.Entities;

import com.esi.mspatient.DTO.OrdonnanceDTO;
import com.esi.mspatient.DTO.RemboursementDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;

    private String nom;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<AntecedentMedical> entecedentMedicalset;

    @Transient
    private List<OrdonnanceDTO> ordonnances;

    @Transient
    private List<RemboursementDTO> remboursements;


}
