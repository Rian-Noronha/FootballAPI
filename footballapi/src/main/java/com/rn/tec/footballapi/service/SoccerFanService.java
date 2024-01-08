package com.rn.tec.footballapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rn.tec.footballapi.model.SoccerFan;
import com.rn.tec.footballapi.model.SoccerTeam;
import com.rn.tec.footballapi.model.dto.SoccerFanTeamDTO;
import com.rn.tec.footballapi.repository.SoccerFanRepository;
import jakarta.mail.internet.InternetAddress;

import org.springframework.util.StringUtils;

@Service
public class SoccerFanService {

	@Autowired
	private SoccerFanRepository soccerFanRepository;

	public void include(SoccerFan soccerFan) {

		if (soccerFan == null) {
			throw new RuntimeException("Soccer fan is null");
		}

		if (soccerFan.getId() != null) {
			soccerFan.setId(null);
		}

		boolean fieldsAreValid = checkFields(soccerFan);
		validateEmail(soccerFan.getEmail());

		if (soccerFanRepository.findByEmail(soccerFan.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}

		if (fieldsAreValid) {
			this.soccerFanRepository.save(soccerFan);
		}

	}

	public List<SoccerFan> list() {
		return soccerFanRepository.findAll();
	}

	public SoccerFan find(Long id) {
		return soccerFanRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("The soccer fan with this ID doesn't exist: " + id));
	}

	public SoccerFanTeamDTO list(Long fanId) {
		SoccerFanTeamDTO soccerFanTeamDTO = new SoccerFanTeamDTO();

		SoccerFan soccerFan = this.soccerFanRepository.findById(fanId)
				.orElseThrow(() -> new RuntimeException("Soccer fan not found"));

		SoccerTeam soccerTeam = soccerFan.getSoccerTeamFan();

		soccerFanTeamDTO.setFanName(soccerFan.getName());
		soccerFanTeamDTO.setFanEmail(soccerFan.getEmail());
		soccerFanTeamDTO.setTeamName(soccerTeam.getName());
		soccerFanTeamDTO.setTeamLogo(soccerTeam.getLogo());

		return soccerFanTeamDTO;

	}

	public void exclude(Long id) {
		if (id == null || id == 0L) {
			throw new RuntimeException("Soccer Fan ID doesn't exist");
		}

		if (!this.soccerFanRepository.existsById(id)) {
			throw new RuntimeException("The soccer fan with this ID doesn't exist: " + id);
		}

		this.soccerFanRepository.deleteById(id);
	}

	public SoccerFan alter(SoccerFan soccerFan) {

		if (soccerFan.getId() == null || soccerFan.getId() == 0L) {
			throw new RuntimeException("Please provide a valid soccer fan identifier");
		}

		if (!this.soccerFanRepository.existsById(soccerFan.getId())) {
			throw new RuntimeException("The soccer fan with ID " + soccerFan.getId() + " does not exist");
		}

		if (checkFields(soccerFan)) {
			validateEmail(soccerFan.getEmail());
			SoccerFan fan = this.soccerFanRepository.findByEmail(soccerFan.getEmail())
					.orElseThrow(() -> new RuntimeException(
							"Failed to retrieve the soccer fan with EMAIL: " + soccerFan.getEmail()));
			fan.setName(soccerFan.getName());
			fan.setSoccerTeamFan(soccerFan.getSoccerTeamFan());
			return this.soccerFanRepository.save(fan);
		} else {
			throw new RuntimeException("Please fill all the required fields");
		}

	}

	private boolean checkFields(SoccerFan soccerFan) {
		boolean isChecked = true;
		if (!StringUtils.hasLength(soccerFan.getName()) || !StringUtils.hasLength(soccerFan.getEmail())

				|| soccerFan.getSoccerTeamFan() == null) {
			isChecked = false;
		}

		return isChecked;
	}

	private void validateEmail(String email) {

		try {
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
		} catch (Exception e) {
			throw new RuntimeException("Invalid email");
		}

	}

}
