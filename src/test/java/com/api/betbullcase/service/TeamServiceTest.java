package com.api.betbullcase.service;

import com.api.betbullcase.builder.TeamBuilder;
import com.api.betbullcase.entity.Team;
import com.api.betbullcase.repository.TeamRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest extends TestCase {

    @InjectMocks
    TeamService service;

    @Mock
    TeamRepository teamRepository;

    @Test
    public void shouldFindAllTeams() {
        service.findAll();

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindById() {
        Long teamId = 1L;

        service.findById(teamId);

        verify(teamRepository, times(1)).findById(teamId);
    }


    @Test
    public void shouldSaveTeam() {
        Team team = TeamBuilder.aTeam().build();

        service.save(team);

        verify(teamRepository, times(1)).save(team);
    }


    @Test
    public void shouldDeleteById() {
        Long teamId = 1L;

        service.deleteById(teamId);

        verify(teamRepository, times(1)).deleteById(teamId);
    }

}