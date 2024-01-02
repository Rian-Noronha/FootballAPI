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

import com.rn.tec.footballapi.model.SoccerPlayer;
import com.rn.tec.footballapi.model.dto.SoccerPlayerTeamDTO;
import com.rn.tec.footballapi.service.SoccerPlayerService;

@RestController
@RequestMapping("/api/soccerplayer")
public class SoccerPlayerAPI {

	@Autowired
	private SoccerPlayerService soccerPlayerService;

	@GetMapping("/soccerplayers")
	@ResponseStatus(HttpStatus.OK)
	public List<SoccerPlayer> listSoccerPlayers() {
		return this.soccerPlayerService.list();
	}
	
	@GetMapping("/soccerplayerteam/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerPlayerTeamDTO listSoccerPlayerTeamDTO(@PathVariable Long id) {
		return this.soccerPlayerService.list(id);
	}

	@DeleteMapping("/soccerplayer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSoccerPlayers(@PathVariable Long id) {
		this.soccerPlayerService.exclude(id);
	}

	@PostMapping("/soccerplayer")
	@ResponseStatus(HttpStatus.CREATED)
	public SoccerPlayer includeSoccerPlayer(@RequestBody SoccerPlayer soccerPlayer) {
		return this.soccerPlayerService.include(soccerPlayer);
	}

	@PutMapping("/soccerplayer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerPlayer alterSoccerPlayer(@PathVariable Long id, @RequestBody SoccerPlayer soccerPlayer) {

		return this.soccerPlayerService.alter(soccerPlayer);

	}

}
