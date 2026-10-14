package com.esi.gateway.DefaultResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    private Long idPatient;
    private String name;
}
