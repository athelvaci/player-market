package com.api.betbullcase.service;

import com.api.betbullcase.builder.PlayerBuilder;
import com.api.betbullcase.builder.TeamBuilder;
import com.api.betbullcase.consts.Currency;
import com.api.betbullcase.entity.Player;
import com.api.betbullcase.entity.Team;
import com.api.betbullcase.repository.PlayerRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest extends TestCase {
    @InjectMocks
    PlayerService service;

    @Mock
    PlayerRepository playerRepository;

    private final BigDecimal TRANSFER_COEFFICIENT_VALUE = new BigDecimal(100000);
    private final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Test
    public void shouldFindAllTeams() {
        service.findAll();

        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindById() {
        Long playerId = 1L;

        service.findById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
    }


    @Test
    public void shouldSavePlayer() {
        Player player = PlayerBuilder.aPlayer().build();

        service.save(player);

        verify(playerRepository, times(1)).save(player);
    }


    @Test
    public void shouldDeleteById() {
        Long playerId = 1L;

        service.deleteById(playerId);

        verify(playerRepository, times(1)).deleteById(playerId);
    }

    @Test
    public void shouldFindTeamsOfPlayersByIds() {
        List<Long> playerIds = Arrays.asList(1L, 2L);
        Team team = TeamBuilder.aTeam().withId(1L).build();
        Player player = PlayerBuilder.aPlayer().withTeam(team).build();
        List<Player> players = Collections.singletonList(player);

        when(playerRepository.findByIdIn(playerIds)).thenReturn(players);

        Set<Team> teamsOfPlayersBy = service.findTeamsOfPlayersBy(playerIds);

        verify(playerRepository, times(1)).findByIdIn(playerIds);
        assertEquals(1, teamsOfPlayersBy.size());
        assertEquals(team, teamsOfPlayersBy.iterator().next());

    }

    @Test
    public void shouldFindTeamOfPlayerById() {
        Long playerId = 1L;
        Team team = TeamBuilder.aTeam().withId(1L).build();
        Player player = PlayerBuilder.aPlayer().withTeam(team).build();
        Optional<Player> optPlayer = Optional.of(player);

        when(playerRepository.findById(playerId)).thenReturn(optPlayer);

        Team teamsOfPlayer = service.findTeamById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
        assertEquals(team, teamsOfPlayer);

    }

    @Test
    public void shouldReturnNullIfTeamOfPlayerNull() {
        Long playerId = 1L;
        Player player = PlayerBuilder.aPlayer().build();
        Optional<Player> optPlayer = Optional.of(player);

        when(playerRepository.findById(playerId)).thenReturn(optPlayer);

        Team teamsOfPlayer = service.findTeamById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
        assertNull(null, teamsOfPlayer);

    }

    @Test
    public void shouldReturnNotExistWhenPlayerNotFoundForContractFee() {
        Long playerId = 1L;
        Optional<Player> optPlayer = Optional.empty();

        when(playerRepository.findById(playerId)).thenReturn(optPlayer);

        String response = service.findContractFeeById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
        assertEquals("Player is not exist with this id", response);
    }

    @Test
    public void shouldReturnZeroIfTransferFeeIsZero() {
        Long playerId = 1L;
        Team team = TeamBuilder.aTeam().withCurrency(Currency.EUR).build();
        Player player = PlayerBuilder.aPlayer().withTeam(team).withBirthDate(new Date()).build();
        Optional<Player> optPlayer = Optional.of(player);

        when(playerRepository.findById(playerId)).thenReturn(optPlayer);

        String contractFeeById = service.findContractFeeById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
        assertEquals("0EUR", contractFeeById);
    }

    @Test
    public void shouldReturnZeroIfAgeIsZero() {
        Long playerId = 1L;
        Team team = TeamBuilder.aTeam().withCurrency(Currency.EUR).build();
        Player player = PlayerBuilder.aPlayer().withTeam(team).build();
        Optional<Player> optPlayer = Optional.of(player);

        when(playerRepository.findById(playerId)).thenReturn(optPlayer);

        String contractFeeById = service.findContractFeeById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
        assertEquals("0EUR", contractFeeById);
    }

    @Test
    public void shouldCalculateContractFee() {
        Long playerId = 1L;
        LocalDate today = LocalDate.now();
        LocalDate fiveYearsMinus = today.minusYears(5);
        Date birthDate = Date.from(fiveYearsMinus.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Team team = TeamBuilder.aTeam().withCommission(new BigDecimal(10)).withCurrency(Currency.EUR).build();
        Player player = PlayerBuilder.aPlayer()
                .withTeam(team)
                .withBirthDate(birthDate)
                .withExperience(2)
                .build();
        Optional<Player> optPlayer = Optional.of(player);

        when(playerRepository.findById(playerId)).thenReturn(optPlayer);

        String contractFeeById = service.findContractFeeById(playerId);

        verify(playerRepository, times(1)).findById(playerId);
        assertEquals("44000.00EUR", contractFeeById);
    }



}