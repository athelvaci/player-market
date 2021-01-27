package com.api.betbullcase.builder;

import com.api.betbullcase.entity.Player;
import com.api.betbullcase.entity.Team;

import java.math.BigDecimal;
import java.util.Date;

public final class PlayerBuilder {
    private Long id;
    private String name;
    private Date birthDate;
    private Integer experience;
    private BigDecimal price;
    private Team team;
    private Date createdAt;
    private Date updatedAt;

    private PlayerBuilder() {
    }

    public static PlayerBuilder aPlayer() {
        return new PlayerBuilder();
    }

    public PlayerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PlayerBuilder withExperience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public PlayerBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PlayerBuilder withTeam(Team team) {
        this.team = team;
        return this;
    }

    public PlayerBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PlayerBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Player build() {
        Player player = new Player();
        player.setId(id);
        player.setName(name);
        player.setBirthDate(birthDate);
        player.setExperience(experience);
        player.setPrice(price);
        player.setTeam(team);
        player.setCreatedAt(createdAt);
        player.setUpdatedAt(updatedAt);
        return player;
    }
}
