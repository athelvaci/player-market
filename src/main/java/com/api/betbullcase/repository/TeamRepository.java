package com.api.betbullcase.repository;

import com.api.betbullcase.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
