package com.esi.msordonnance.Proxy;

import com.esi.msordonnance.DTO.RemboursementDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-remboursement")
@LoadBalancerClient("ms-remboursement")
public interface RemboursementProxy {

    @GetMapping("/api/remboursement/ordonnance/{idOrdonnance}")
    RemboursementDTO getRemboursementByOrdonnance(@PathVariable("idOrdonnance") Long idOrdonnance);
}
