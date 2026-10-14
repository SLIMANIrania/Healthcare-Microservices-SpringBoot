package com.esi.msordonnance.Proxy;

import com.esi.msordonnance.DTO.PatientDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-patient" )
@LoadBalancerClient("ms-patient")
public interface PatientProxy {

    @GetMapping("/patients/{id}")
    @CircuitBreaker(name="fall" , fallbackMethod = "fallbackPatient")
    @Cacheable(value = "MyValue" , key="#idp")
    public PatientDTO getPatientDTO(@PathVariable("id") Long idp);

    default PatientDTO fallbackPatient(Long idp,Throwable throwable ){
        return new PatientDTO("uknown");
    }
}



