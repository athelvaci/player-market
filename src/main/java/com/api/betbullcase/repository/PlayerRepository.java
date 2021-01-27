package com.api.betbullcase.repository;

import com.api.betbullcase.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByIdIn(List<Long> ids);
}
