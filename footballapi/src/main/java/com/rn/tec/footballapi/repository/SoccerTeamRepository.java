package com.rn.tec.footballapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rn.tec.footballapi.model.SoccerTeam;

@Repository
public interface SoccerTeamRepository extends JpaRepository<SoccerTeam, Long>{

}
