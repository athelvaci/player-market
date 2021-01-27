package com.api.betbullcase.builder;

import com.api.betbullcase.consts.Currency;
import com.api.betbullcase.entity.Player;
import com.api.betbullcase.entity.Team;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public final class TeamBuilder {
    private Long id;
    private String name;
    private Currency currency;
    private BigDecimal commission;
    private Set<Player> players;
    private Date createdAt;
    private Date updatedAt;

    private TeamBuilder() {
    }

    public static TeamBuilder aTeam() {
        return new TeamBuilder();
    }

    public TeamBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TeamBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TeamBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public TeamBuilder withCommission(BigDecimal commission) {
        this.commission = commission;
        return this;
    }

    public TeamBuilder withPlayers(Set<Player> players) {
        this.players = players;
        return this;
    }

    public TeamBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TeamBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Team build() {
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        team.setCurrency(currency);
        team.setCommission(commission);
        team.setPlayers(players);
        team.setCreatedAt(createdAt);
        team.setUpdatedAt(updatedAt);
        return team;
    }
}
