package com.api.betbullcase.dtos;

import com.api.betbullcase.consts.Currency;
import lombok.Data;

@Data
public class TeamDTO {

    private String name;
    private Currency currency;
    private Double commission;

}
