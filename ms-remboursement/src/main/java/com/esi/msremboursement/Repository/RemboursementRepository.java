package com.esi.msremboursement.Repository;

import com.esi.msremboursement.Entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement,Long> {
    List<Remboursement> findByAssure_IdAssure(Long idAssure);

    Remboursement findByIdOrdonnance(Long idOrdonnance);
}
