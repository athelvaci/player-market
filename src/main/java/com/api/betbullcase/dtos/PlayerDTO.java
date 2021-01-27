package com.api.betbullcase.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PlayerDTO {
    private String name;
    private Date birthDate;
    private Integer experience;
    private BigDecimal price;
    private Integer teamId;

}
