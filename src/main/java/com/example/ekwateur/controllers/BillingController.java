package com.example.ekwateur.controllers;


import com.example.ekwateur.DTO.BusinessClient;
import com.example.ekwateur.DTO.RetailClient;
import com.example.ekwateur.services.BillingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/billing", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping(
            value = "/calculateBusinessCharge",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BigDecimal calculateBusinessCharge(@RequestBody @Valid BusinessClient input) {
        return billingService.computeBusinessCharge(input);
    }

    @PostMapping(
            value = "/calculateRetailCharge",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BigDecimal calculateRetailCharge(@RequestBody @Valid RetailClient input) {
        return billingService.computeRetailCharge(input);
    }


}
