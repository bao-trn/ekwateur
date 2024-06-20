package com.example.ekwateur.services;

import com.example.ekwateur.DTO.BusinessClient;
import com.example.ekwateur.DTO.RetailClient;
import com.example.ekwateur.constants.EnergyConstant;
import com.example.ekwateur.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BillingServiceTest {

    @Mock
    BillingService service;

    @BeforeEach
    void setupEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_computeBusinessCharge() {
        BusinessClient businessClient = mock(BusinessClient.class);

        when(service.computeTotalAmount(anyDouble())).thenCallRealMethod();
        when(service.computeBusinessCharge(any(BusinessClient.class))).thenCallRealMethod();

        when(businessClient.getTurnover())
                .thenReturn(1_000_000L)
                .thenReturn(999_999L)
                .thenReturn(1_000_000L)
                .thenReturn(999_999L);

        when(businessClient.getEnergyType())
                .thenReturn("E")
                .thenReturn("E")
                .thenReturn("G")
                .thenReturn("G");

        assertEquals(service.computeTotalAmount(EnergyConstant.LARGE_BUSINESS_ELECTRICITY_PRICE), service.computeBusinessCharge(businessClient));
        assertEquals(service.computeTotalAmount(EnergyConstant.SMALL_BUSINESS_ELECTRICITY_PRICE), service.computeBusinessCharge(businessClient));
        assertEquals(service.computeTotalAmount(EnergyConstant.LARGE_BUSINESS_GAS_PRICE), service.computeBusinessCharge(businessClient));
        assertEquals(service.computeTotalAmount(EnergyConstant.SMALL_BUSINESS_GAS_PRICE), service.computeBusinessCharge(businessClient));
    }

    @Test
    void test_computeRetailCharge() {
        RetailClient retailClient = mock(RetailClient.class);

        when(service.computeTotalAmount(anyDouble())).thenCallRealMethod();
        when(service.computeRetailCharge(any(RetailClient.class))).thenCallRealMethod();

        when(retailClient.getEnergyType())
                .thenReturn("E")
                .thenReturn("G");

        assertEquals(service.computeTotalAmount(EnergyConstant.RETAIL_ELECTRICITY_PRICE), service.computeRetailCharge(retailClient));
        assertEquals(service.computeTotalAmount(EnergyConstant.RETAIL_GAS_PRICE), service.computeRetailCharge(retailClient));
    }

    @Test
    void test_computeTotalAmount() {
        when(service.computeTotalAmount(anyDouble())).thenCallRealMethod();

        assertEquals(BigDecimal.valueOf(DateUtils.hoursInCurrentMonths() * EnergyConstant.RETAIL_ELECTRICITY_PRICE).setScale(2, RoundingMode.HALF_UP), service.computeTotalAmount(EnergyConstant.RETAIL_ELECTRICITY_PRICE));
    }
}
