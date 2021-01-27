package com.api.betbullcase.controller;

import com.api.betbullcase.entity.Team;
import com.api.betbullcase.dtos.TeamDTO;
import com.api.betbullcase.mapper.TeamMapper;
import com.api.betbullcase.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/teams")
public class TeamAPI {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAll() {
        return ResponseEntity.ok(teamMapper.toTeamDTOs(teamService.findAll()));
    }

    @PostMapping
    public ResponseEntity<TeamDTO> create(@RequestBody TeamDTO teamDTO) {
        Team team = teamMapper.toTeam(teamDTO);
        teamService.save(team);

        return ResponseEntity.status(HttpStatus.CREATED).body(teamDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id) {
        Optional<Team> team = teamService.findById(id);

        return ResponseEntity.ok(teamMapper.toTeamDTO(team.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Team team = teamMapper.toTeam(teamDTO);
        team.setId(id);

        teamService.save(team);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        teamService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
