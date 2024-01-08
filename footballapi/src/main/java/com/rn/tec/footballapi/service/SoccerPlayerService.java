package com.rn.tec.footballapi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rn.tec.footballapi.model.SoccerFan;
import com.rn.tec.footballapi.model.SoccerPlayer;
import com.rn.tec.footballapi.model.SoccerTeam;
import com.rn.tec.footballapi.model.dto.SoccerPlayerTeamDTO;
import com.rn.tec.footballapi.repository.SoccerPlayerRepository;
import com.rn.tec.footballapi.repository.SoccerTeamRepository;

@Service
public class SoccerPlayerService {

	@Autowired
	private SoccerPlayerRepository soccerPlayerRepository;

	public SoccerPlayer include(SoccerPlayer soccerPlayer) {

		soccerPlayer.setId(null);

		if(checkFields(soccerPlayer)) {
			return this.soccerPlayerRepository.save(soccerPlayer);
		}else {
			throw new RuntimeException("Please fill all the required fields");
		}

	}

	public List<SoccerPlayer> list() {
		return soccerPlayerRepository.findAll();
	}

	public SoccerPlayer find(Long id) {
		return soccerPlayerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("The soccer fan with this ID doesn't exist: " + id));
	}

	public SoccerPlayerTeamDTO list(Long playerId) {

		if (playerId == null || playerId == 0) {
			throw new IllegalArgumentException("Invalid playerId");
		}

		SoccerPlayer soccerPlayer = this.soccerPlayerRepository.findById(playerId)
				.orElseThrow(() -> new RuntimeException("Soccer player not found"));

		if (soccerPlayer.getSoccerTeamPlayer() == null) {
			throw new RuntimeException("Soccer player does not have an associated team");
		}

		SoccerPlayerTeamDTO soccerPlayerTeamDTO = new SoccerPlayerTeamDTO();

		soccerPlayerTeamDTO.setPlayerName(soccerPlayer.getName());
		soccerPlayerTeamDTO.setPlayerImage(soccerPlayer.getImage());

		SoccerTeam soccerTeam = soccerPlayer.getSoccerTeamPlayer();

		soccerPlayerTeamDTO.setTeamName(soccerTeam.getName());
		soccerPlayerTeamDTO.setTeamLogo(soccerTeam.getLogo());

		return soccerPlayerTeamDTO;
	}

	public void exclude(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Soccer Player ID doesn't exist");
		}

		if (!this.soccerPlayerRepository.existsById(id)) {
			throw new RuntimeException("The soccer player with this ID doesn't exist: " + id);
		}

		this.soccerPlayerRepository.deleteById(id);
	}

	public SoccerPlayer alter(SoccerPlayer soccerPlayer) {
		if (soccerPlayer.getId() == null || soccerPlayer.getId() == 0L) {
			throw new RuntimeException("Please provide a valid soccer player identifier");
		}

		if (!this.soccerPlayerRepository.existsById(soccerPlayer.getId())) {
			throw new RuntimeException("The soccer player with ID " + soccerPlayer.getId() + " does not exist");
		}

		if (checkFields(soccerPlayer)) {
			SoccerPlayer player = this.soccerPlayerRepository.findById(soccerPlayer.getId()).orElseThrow(
					() -> new RuntimeException("Failed to retrieve the soccer player with ID: " + soccerPlayer.getId()));

			player.setName(soccerPlayer.getName());
			player.setCareerGoals(soccerPlayer.getCareerGoals());
			player.setImage(soccerPlayer.getImage());
			player.setHeight(soccerPlayer.getHeight());
			player.setPosition(soccerPlayer.getPosition());
			player.setSalary(soccerPlayer.getSalary());
			player.setWeight(soccerPlayer.getWeight());
			player.setSellingPrice(soccerPlayer.getSellingPrice());
			player.setSoccerTeamPlayer(soccerPlayer.getSoccerTeamPlayer());

			return this.soccerPlayerRepository.save(player);
		} else {
			throw new RuntimeException("Please fill all the required fields");
		}
	}

	private boolean checkFields(SoccerPlayer soccerPlayer) {

		boolean isChecked = true;

		if (!StringUtils.hasLength(soccerPlayer.getName()) || !StringUtils.hasLength(soccerPlayer.getImage())
				|| !StringUtils.hasLength(soccerPlayer.getPosition())

				|| soccerPlayer.getCareerGoals() == 0 || soccerPlayer.getHeight() == 0 || soccerPlayer.getWeight() == 0
				|| soccerPlayer.getSellingPrice().compareTo(BigDecimal.ZERO) == 0 || soccerPlayer.getSalary() == 0
				|| soccerPlayer.getSoccerTeamPlayer() == null) {

			isChecked = false;
		}

		return isChecked;
	}

}
