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

import com.rn.tec.footballapi.model.SoccerFan;
import com.rn.tec.footballapi.model.dto.SoccerFanTeamDTO;
import com.rn.tec.footballapi.model.dto.SoccerPlayerTeamDTO;
import com.rn.tec.footballapi.service.SoccerFanService;


@RestController
@RequestMapping("/api/soccerfan")
public class SoccerFanAPI {

	@Autowired
	private SoccerFanService soccerFanService;

	@GetMapping("/soccerfans")
	@ResponseStatus(HttpStatus.OK)
	public List<SoccerFan> listSoccerFans() {
		return this.soccerFanService.list();
	}
	
	@GetMapping("/soccerfan/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerFan findSoccerFan(@PathVariable Long id) {
		return this.soccerFanService.find(id);
	}
	
	
	@GetMapping("/soccerfanteam/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerFanTeamDTO listSoccerFanTeam(@PathVariable Long id) {
		return this.soccerFanService.list(id);
	}
	
	@DeleteMapping("/soccerfan/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSoccerFans(@PathVariable Long id) {
		this.soccerFanService.exclude(id);
	}

	@PostMapping("/soccerfan")
	@ResponseStatus(HttpStatus.CREATED)
	public void includeSoccerFan(@RequestBody SoccerFan soccerFan) {
		this.soccerFanService.include(soccerFan);
	}

	@PutMapping("/soccerfan/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerFan alterSoccerFan(@PathVariable Long id, @RequestBody SoccerFan soccerFan) {

		return this.soccerFanService.alter(soccerFan);

	}

}
