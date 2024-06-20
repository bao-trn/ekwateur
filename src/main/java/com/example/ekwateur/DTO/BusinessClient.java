package com.example.ekwateur.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessClient extends CommonClientDTO {

    private int siretNumber;

    private String businessName;

    private long turnover;

}
