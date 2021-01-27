package com.api.betbullcase.builder;

import com.api.betbullcase.dtos.PlayerDTO;

import java.math.BigDecimal;
import java.util.Date;

public final class PlayerDTOBuilder {
    private String name;
    private Date birthDate;
    private Integer experience;
    private BigDecimal price;
    private Integer teamId;

    private PlayerDTOBuilder() {
    }

    public static PlayerDTOBuilder aPlayerDTO() {
        return new PlayerDTOBuilder();
    }

    public PlayerDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerDTOBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PlayerDTOBuilder withExperience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public PlayerDTOBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PlayerDTOBuilder withTeamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    public PlayerDTO build() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(name);
        playerDTO.setBirthDate(birthDate);
        playerDTO.setExperience(experience);
        playerDTO.setPrice(price);
        playerDTO.setTeamId(teamId);
        return playerDTO;
    }
}
