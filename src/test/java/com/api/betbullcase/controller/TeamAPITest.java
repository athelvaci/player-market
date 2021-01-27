package com.api.betbullcase.controller;

import com.api.betbullcase.builder.TeamBuilder;
import com.api.betbullcase.builder.TeamDTOBuilder;
import com.api.betbullcase.dtos.TeamDTO;
import com.api.betbullcase.entity.Team;
import com.api.betbullcase.mapper.PlayerMapper;
import com.api.betbullcase.mapper.TeamMapper;
import com.api.betbullcase.service.TeamService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamAPITest extends TestCase {
    @InjectMocks
    TeamAPI api;

    @Mock
    TeamService teamService;

    @Mock
    TeamMapper teamMapper;

    @Mock
    PlayerMapper playerMapper;

    @Test
    public void shouldFindAllTeams() {
        String teamName = "name";
        Team team = TeamBuilder.aTeam().withName(teamName).build();
        TeamDTO teamDTO = TeamDTOBuilder.aTeamDTO().withName(team.getName()).build();
        List<Team> teamList = Collections.singletonList(team);
        List<TeamDTO> teamDTOS = Collections.singletonList(teamDTO);

        when(teamService.findAll()).thenReturn(teamList);
        when(teamMapper.toTeamDTOs(teamList)).thenReturn(teamDTOS);

        ResponseEntity<List<TeamDTO>> responseTeams = api.findAll();
        List<TeamDTO> responseTeamsBody = responseTeams.getBody();

        assertNotNull(responseTeamsBody);
        assertEquals(teamList.size(), responseTeamsBody.size());
        assertEquals(team.getName(), responseTeamsBody.get(0).getName());

    }

    @Test
    public void shouldCreateTeam() {
        Team team = TeamBuilder.aTeam().build();
        TeamDTO teamDTO = TeamDTOBuilder.aTeamDTO().build();

        when(teamMapper.toTeam(teamDTO)).thenReturn(team);

        api.create(teamDTO);

        verify(teamService, times(1)).save(team);
    }

    @Test
    public void shouldFindById() {
        Long teamId = 1L;
        Team team = TeamBuilder.aTeam().withId(teamId).build();
        TeamDTO teamDTO = TeamDTOBuilder.aTeamDTO().build();
        Optional<Team> teamOpt = Optional.of(team);

        when(teamMapper.toTeamDTO(team)).thenReturn(teamDTO);
        when(teamService.findById(teamId)).thenReturn(teamOpt);

        ResponseEntity<TeamDTO> responseTeam = api.findById(teamId);


        assertNotNull(responseTeam);
        assertEquals(teamDTO, responseTeam.getBody());
    }

    @Test
    public void shouldUpdateTeam() {
        Long teamId = 1L;
        Team team = TeamBuilder.aTeam().withId(teamId).build();
        TeamDTO teamDTO = TeamDTOBuilder.aTeamDTO().build();

        when(teamMapper.toTeam(teamDTO)).thenReturn(team);

        api.update(teamId, teamDTO);

        verify(teamService, times(1)).save(team);
    }

    @Test
    public void shouldDeleteTeam() {
        Long teamId = 1L;

        api.delete(teamId);

        verify(teamService, times(1)).deleteById(teamId);
    }

}