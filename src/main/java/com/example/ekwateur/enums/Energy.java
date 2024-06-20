package com.example.ekwateur.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Energy {

    ELECTRICITY("E"),
    GAS("G");


    private final String id;

    public static Energy getValueFromId(String id) {
        return Arrays.stream(Energy.values())
                .filter(value -> value.getId().equals(id))
                .findFirst().orElse(null);
    }

}
