package com.esi.msordonnance.Repository;

import com.esi.msordonnance.Entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
}
