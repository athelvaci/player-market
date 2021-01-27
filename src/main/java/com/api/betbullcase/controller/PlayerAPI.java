package com.api.betbullcase.controller;

import com.api.betbullcase.dtos.PlayerDTO;
import com.api.betbullcase.entity.Player;
import com.api.betbullcase.mapper.PlayerMapper;
import com.api.betbullcase.service.PlayerService;
import com.api.betbullcase.entity.Team;
import com.api.betbullcase.dtos.TeamDTO;
import com.api.betbullcase.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/players")
public class PlayerAPI {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;
    private final TeamMapper teamMapper;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return ResponseEntity.ok(playerMapper.toPlayerDTOs(playerService.findAll()));
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerDTO playerDTO) {
        Player player = playerMapper.toPlayer(playerDTO);
        playerService.save(player);

        return ResponseEntity.status(HttpStatus.CREATED).body(playerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable Long id) {
        Optional<Player> player = playerService.findById(id);

        return ResponseEntity.ok(playerMapper.toPlayerDTO(player.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player player = playerMapper.toPlayer(playerDTO);
        player.setId(id);

        playerService.save(player);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        playerService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("{ids}/teams")
    public ResponseEntity<List<TeamDTO>> findTeamsByIds(@PathVariable List<Long> ids) {
        Set<Team> teams = playerService.findTeamsOfPlayersBy(ids);

        return ResponseEntity.ok(teamMapper.toTeamDTOs(teams));
    }

    @GetMapping("{id}/team")
    public ResponseEntity<TeamDTO> findTeamById(@PathVariable Long id) {
        Team team = playerService.findTeamById(id);

        return ResponseEntity.ok(teamMapper.toTeamDTO(team));
    }

    @GetMapping("{id}/contractFee")
    public ResponseEntity<String> findContractFeeById(@PathVariable Long id) {
        String contractFee = playerService.findContractFeeById(id);

        return ResponseEntity.ok(contractFee);
    }

}
