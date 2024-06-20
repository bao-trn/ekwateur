package com.example.ekwateur.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetailClient extends CommonClientDTO {

    private String civility;

    private String lastName;

    private String firstName;

}
