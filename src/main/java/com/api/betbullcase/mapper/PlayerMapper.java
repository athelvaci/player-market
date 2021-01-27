package com.api.betbullcase.mapper;

import com.api.betbullcase.dtos.PlayerDTO;
import com.api.betbullcase.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PlayerMapper {
    @Mapping(source = "team.id",target = "teamId")
    PlayerDTO toPlayerDTO(Player player);

    List<PlayerDTO> toPlayerDTOs(List<Player> players);

    @Mapping(source = "teamId",target = "team.id")
    Player toPlayer(PlayerDTO playerDTO);
}
