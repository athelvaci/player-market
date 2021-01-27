package com.api.betbullcase.mapper;

import com.api.betbullcase.entity.Team;
import com.api.betbullcase.dtos.TeamDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface TeamMapper {
    TeamDTO toTeamDTO(Team team);

    List<TeamDTO> toTeamDTOs(List<Team> teams);

    List<TeamDTO> toTeamDTOs(Set<Team> teams);

    Team toTeam(TeamDTO teamDTO);
}
