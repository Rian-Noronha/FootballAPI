package com.rn.tec.footballapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rn.tec.footballapi.model.SoccerFan;

@Repository
public interface SoccerFanRepository extends JpaRepository<SoccerFan, Long>{

}
