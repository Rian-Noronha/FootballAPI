package com.rn.tec.footballapi.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.rn.tec.footballapi.model.SoccerTeam;
import com.rn.tec.footballapi.service.SoccerTeamService;

@RestController
@RequestMapping("/api/soccerteam")
public class SoccerTeamAPI {
	
	@Autowired
	private SoccerTeamService soccerTeamService;

	@GetMapping("/soccerteams")
	@ResponseStatus(HttpStatus.OK)
	public List<SoccerTeam> listSoccerTeams() {
		return this.soccerTeamService.list();
	}

	@DeleteMapping("/soccerteam/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSoccerTeams(@PathVariable Long id) {
		this.soccerTeamService.exclude(id);
	}

	@PostMapping("/soccerteam")
	@ResponseStatus(HttpStatus.CREATED)
	public SoccerTeam includeSoccerTeam(@RequestBody SoccerTeam soccerTeam) {
		return this.soccerTeamService.include(soccerTeam);
	}

	@PutMapping("/soccerteam/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerTeam alterSoccerTeam(@PathVariable Long id, @RequestBody SoccerTeam soccerTeam) {

		return this.soccerTeamService.alter(soccerTeam);

	}
	

}
