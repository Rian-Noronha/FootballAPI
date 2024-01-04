package com.rn.tec.footballapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rn.tec.footballapi.model.SoccerFan;

@Repository
public interface SoccerFanRepository extends JpaRepository<SoccerFan, Long>{
	Optional<SoccerFan> findByEmail(String email);
}
