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

	@DeleteMapping("/soccerfan/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSoccerFans(@PathVariable Long id) {
		this.soccerFanService.exclude(id);
	}

	@PostMapping("/soccerfan")
	@ResponseStatus(HttpStatus.CREATED)
	public SoccerFan includeSoccerFan(@RequestBody SoccerFan soccerFan) {
		return this.soccerFanService.include(soccerFan);
	}

	@PutMapping("/soccerfan/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SoccerFan alterSoccerFan(@PathVariable Long id, @RequestBody SoccerFan soccerFan) {

		return this.soccerFanService.alter(soccerFan);

	}

}
