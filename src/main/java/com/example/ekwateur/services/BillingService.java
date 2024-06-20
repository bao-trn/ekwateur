package com.example.ekwateur.services;

import com.example.ekwateur.DTO.BusinessClient;
import com.example.ekwateur.DTO.RetailClient;
import com.example.ekwateur.constants.EnergyConstant;
import com.example.ekwateur.enums.Energy;
import com.example.ekwateur.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BillingService {

    public BigDecimal computeBusinessCharge(BusinessClient client) {
        //ensure clientInfo;
        switch (Energy.getValueFromId(client.getEnergyType())) {

            case ELECTRICITY -> {
                return client.getTurnover() >= EnergyConstant.BUSINESS_TURNOVER_THRESHOLD ?
                        computeTotalAmount(EnergyConstant.LARGE_BUSINESS_ELECTRICITY_PRICE) :
                        computeTotalAmount(EnergyConstant.SMALL_BUSINESS_ELECTRICITY_PRICE);
            }
            case GAS -> {
                return client.getTurnover() >= EnergyConstant.BUSINESS_TURNOVER_THRESHOLD ?
                        computeTotalAmount(EnergyConstant.LARGE_BUSINESS_GAS_PRICE) :
                        computeTotalAmount(EnergyConstant.SMALL_BUSINESS_GAS_PRICE);
            }

            default -> throw new EnumConstantNotPresentException(Energy.class, "EnergyType not available");
        }
    }

    public BigDecimal computeRetailCharge(RetailClient client) {
        //ensure clientInfo
        switch (Energy.getValueFromId(client.getEnergyType())) {
            case ELECTRICITY -> {
                return computeTotalAmount(EnergyConstant.RETAIL_ELECTRICITY_PRICE);
            }
            case GAS -> {
                return computeTotalAmount(EnergyConstant.RETAIL_GAS_PRICE);
            }
            default -> throw new EnumConstantNotPresentException(Energy.class, "EnergyType not available");
        }
    }

    protected BigDecimal computeTotalAmount(double energyPrice) {
        return BigDecimal.valueOf(DateUtils.hoursInCurrentMonths() * energyPrice).setScale(2, RoundingMode.HALF_UP);
    }

}
