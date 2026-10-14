package com.esi.msremboursement.Graphql;

import com.esi.msremboursement.Entities.Assure;
import com.esi.msremboursement.Entities.Remboursement;
import com.esi.msremboursement.Repository.AssureRepository;
import com.esi.msremboursement.Repository.RemboursementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RemboursemenrResolver {

    private final RemboursementRepository repo;

    private final AssureRepository assureRepo; // si tu veux getAssureById

    @QueryMapping
    public Assure getAssureById(@Argument Long idAssure) {
        return assureRepo.findById(idAssure).orElse(null);
    }

    @QueryMapping
    public List<Remboursement> getRemboursementsByAssure(@Argument Long idAssure) {
        return repo.findByAssure_IdAssure(idAssure);
    }

    @QueryMapping
    public Remboursement getRemboursementByOrdonnance(@Argument Long idOrdonnance) {
        return repo.findByIdOrdonnance(idOrdonnance);
    }
}
