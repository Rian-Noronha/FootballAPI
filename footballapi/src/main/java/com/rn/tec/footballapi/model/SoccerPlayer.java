package com.rn.tec.footballapi.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class SoccerPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double salary;
	private String position;
	private String name;
	private String image;
	private double weight;
	private double height;
	private int careerGoals;
	private BigDecimal sellingPrice;
	
	@ManyToOne
	@JsonIgnore
	private SoccerTeam soccerTeamPlayer;
	

}
