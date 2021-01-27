package com.api.betbullcase.service;

import com.api.betbullcase.entity.Player;
import com.api.betbullcase.entity.Team;
import com.api.betbullcase.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class PlayerService {

    private final BigDecimal TRANSFER_COEFFICIENT_VALUE = new BigDecimal(100000);
    private final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private final PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public Set<Team> findTeamsOfPlayersBy(List<Long> ids) {
        List<Player> players = playerRepository.findByIdIn(ids);
        return players.stream()
                .map(Player::getTeam)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Team findTeamById(Long id) {
        Optional<Player> player = playerRepository.findById(id);

        return player.map(Player::getTeam).orElse(null);
    }

    public String findContractFeeById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (!player.isPresent()) {
            return "Player is not exist with this id";
        }
        BigDecimal contractFee = calculateContractFee(player.get());
        return contractFee + player.get().getTeam().getCurrency().toString();
    }

    private BigDecimal calculateContractFee(Player player) {
        BigDecimal transferFee = calculateTransferFee(player);
        if (transferFee.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal teamCommission = transferFee.multiply(player.getTeam().getCommission())
                .divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
        return transferFee.add(teamCommission);
    }

    private BigDecimal calculateTransferFee(Player player) {
        int ageOfPlayer = findAge(player.getBirthDate());
        if (ageOfPlayer == 0) {
            return new BigDecimal(BigInteger.ZERO);
        }
        return new BigDecimal(player.getExperience()).multiply(TRANSFER_COEFFICIENT_VALUE)
                .divide(new BigDecimal(ageOfPlayer), 2, RoundingMode.HALF_UP);
    }

    private int findAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate localBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(localBirthDate, LocalDate.now()).getYears();
    }

}
