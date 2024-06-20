package com.example.ekwateur.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonClientDTO {

    @Valid
    @NotNull
    @Pattern(regexp = "^EKW\\d{9}$", message = "EKW + 9 numerical values")
    private String clientRef;

    @Valid
    @NotNull(message = "energyType must not be null")
    private String energyType;

}
