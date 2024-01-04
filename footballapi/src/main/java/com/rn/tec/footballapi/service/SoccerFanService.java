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
	    
	    if(soccerFan == null) {
	    	throw new RuntimeException("Soccer fan is null");
	    }
	    
	    soccerFan.setId(null);
	    checkFields(soccerFan);
	    validateEmail(soccerFan.getEmail());
	    
	    if(soccerFanRepository.findByEmail(soccerFan.getEmail()).isPresent()) {
	    	throw new RuntimeException("Email already cadastre");
	    }
	    
	    
	    soccerFanRepository.save(soccerFan);
	    
		
	}



	
	public List<SoccerFan> list(){
		return soccerFanRepository.findAll();
	}
	
	public SoccerFan find(Long id) {
		return soccerFanRepository.findById(id).orElseThrow(() -> new RuntimeException("The soccer fan with this ID doesn't exist: " + id)); 
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
		if(id == null || id == 0L) {
			throw new RuntimeException("Soccer Fan ID doesn't exist");
		}
		
		if(!this.soccerFanRepository.existsById(id)) {
			throw new RuntimeException("The soccer fan with this ID doesn't exist: " + id);
		}
		
		this.soccerFanRepository.deleteById(id);
	}
	
	public SoccerFan alter(SoccerFan soccerFan) {
		
		
		validateEmail(soccerFan.getEmail());
		
		checkFields(soccerFan);
		
		if(soccerFanRepository.findByEmail(soccerFan.getEmail()).isPresent()) {
	    	throw new RuntimeException("Email already cadastre");
	    }
		
		SoccerFan fan = soccerFanRepository.findByEmail(soccerFan.getEmail()).get();
		
		soccerFan.setId(fan.getId());
		
		if(soccerFan.getId() == null || soccerFan.getId() == 0L) {
			throw new RuntimeException("Inform the soccer fan identifier");
		}
		
		if(!this.soccerFanRepository.existsById(soccerFan.getId())) {
			throw new RuntimeException("The soccer fan with this ID doesn't exist: " + soccerFan.getId());
		}
		
		
		
		return this.soccerFanRepository.save(soccerFan);
		
	}
	
	
	private void checkFields(SoccerFan soccerFan) {
		if(!StringUtils.hasLength(soccerFan.getName()) || !StringUtils.hasLength(soccerFan.getEmail())
				
				|| soccerFan.getSoccerTeamFan() == null
				) {
			throw new RuntimeException("Fill in all fields");
		}
	}
	
	private void validateEmail(String email) {
		
		try {
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
		}catch(Exception e) {
			throw new RuntimeException("Invalid email");
		}
		
	}
	
	
}
