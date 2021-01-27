package com.api.betbullcase.controller;

import com.api.betbullcase.builder.PlayerBuilder;
import com.api.betbullcase.builder.PlayerDTOBuilder;
import com.api.betbullcase.dtos.PlayerDTO;
import com.api.betbullcase.entity.Player;
import com.api.betbullcase.mapper.PlayerMapper;
import com.api.betbullcase.mapper.TeamMapper;
import com.api.betbullcase.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PlayerAPITest {
    @InjectMocks
    PlayerAPI api;

    @Mock
    PlayerService playerService;

    @Mock
    PlayerMapper playerMapper;

    @Mock
    TeamMapper teamMapper;

    @Test
    public void shouldFindAllPlayers() {
        String playerName = "name";
        Player player = PlayerBuilder.aPlayer().withName(playerName).build();
        PlayerDTO playerDTO = PlayerDTOBuilder.aPlayerDTO().withName(player.getName()).build();
        List<Player> playerList = Collections.singletonList(player);
        List<PlayerDTO> playerDTOS = Collections.singletonList(playerDTO);

        when(playerService.findAll()).thenReturn(playerList);
        when(playerMapper.toPlayerDTOs(playerList)).thenReturn(playerDTOS);

        ResponseEntity<List<PlayerDTO>> responsePlayers = api.findAll();
        List<PlayerDTO> responsePlayersBody = responsePlayers.getBody();

        assertNotNull(responsePlayersBody);
        assertEquals(playerList.size(), responsePlayersBody.size());
        assertEquals(player.getName(), responsePlayersBody.get(0).getName());

    }

    @Test
    public void shouldCreatePlayer() {
        Player player = PlayerBuilder.aPlayer().build();
        PlayerDTO playerDTO = PlayerDTOBuilder.aPlayerDTO().build();

        when(playerMapper.toPlayer(playerDTO)).thenReturn(player);

        api.create(playerDTO);

        verify(playerService, times(1)).save(player);
    }

    @Test
    public void shouldFindById() {
        Long playerId = 1L;
        Player player = PlayerBuilder.aPlayer().withId(playerId).build();
        PlayerDTO playerDTO = PlayerDTOBuilder.aPlayerDTO().build();
        Optional<Player> playerOpt = Optional.of(player);

        when(playerMapper.toPlayerDTO(player)).thenReturn(playerDTO);
        when(playerService.findById(playerId)).thenReturn(playerOpt);

        ResponseEntity<PlayerDTO> responsePlayer = api.findById(playerId);


        assertNotNull(responsePlayer);
        assertEquals(playerDTO, responsePlayer.getBody());
    }

    @Test
    public void shouldUpdatePlayer() {
        Long playerId = 1L;
        Player player = PlayerBuilder.aPlayer().withId(playerId).build();
        PlayerDTO playerDTO = PlayerDTOBuilder.aPlayerDTO().build();

        when(playerMapper.toPlayer(playerDTO)).thenReturn(player);

        api.update(playerId, playerDTO);

        verify(playerService, times(1)).save(player);
    }

    @Test
    public void shouldDeletePlayer() {
        Long playerId = 1L;

        api.delete(playerId);

        verify(playerService, times(1)).deleteById(playerId);
    }

    @Test
    public void shouldFindTeamsByPlayerIds() {
        List<Long> playerIds = Arrays.asList(1L, 2L);

        api.findTeamsByIds(playerIds);

        verify(playerService, times(1)).findTeamsOfPlayersBy(playerIds);
    }

    @Test
    public void shouldFindTeamByPlayerId() {
        Long playerId = 1L;

        api.findTeamById(playerId);

        verify(playerService, times(1)).findTeamById(playerId);
    }

    @Test
    public void shouldFindContractFeeById() {
        Long playerId = 1L;

        api.findContractFeeById(playerId);

        verify(playerService, times(1)).findContractFeeById(playerId);
    }


}