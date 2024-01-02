package com.rn.tec.footballapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class SoccerTeam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String logo;
	private LocalDate dateFoundation;
	private String country;
	private BigDecimal evaluated;
	
	@OneToMany(mappedBy = "soccerTeamPlayer")
	private List<SoccerPlayer> soccerPlayers;
	
	@OneToMany(mappedBy = "soccerTeamFan")
	@JsonManagedReference
	private List<SoccerFan> soccerFans;
	

}
