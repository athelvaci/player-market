package com.api.betbullcase.builder;

import com.api.betbullcase.consts.Currency;
import com.api.betbullcase.dtos.TeamDTO;

public final class TeamDTOBuilder {
    private String name;
    private Currency currency;
    private Double commission;

    private TeamDTOBuilder() {
    }

    public static TeamDTOBuilder aTeamDTO() {
        return new TeamDTOBuilder();
    }

    public TeamDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TeamDTOBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public TeamDTOBuilder withCommission(Double commission) {
        this.commission = commission;
        return this;
    }

    public TeamDTO build() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(name);
        teamDTO.setCurrency(currency);
        teamDTO.setCommission(commission);
        return teamDTO;
    }
}
