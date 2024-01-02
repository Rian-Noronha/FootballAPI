package com.rn.tec.footballapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rn.tec.footballapi.model.SoccerFan;
import com.rn.tec.footballapi.model.SoccerTeam;
import com.rn.tec.footballapi.repository.SoccerFanRepository;
import com.rn.tec.footballapi.repository.SoccerTeamRepository;


@Service
public class SoccerFanService {
	
	@Autowired
	private SoccerFanRepository soccerFanRepository;
	
	@Autowired
	private SoccerTeamRepository soccerTeamRepository;
	
	public SoccerFan include(SoccerFan soccerFan) {
	    // Aplicar regras de negócio
	    // 1. Preenchimento de campos obrigatórios
	    soccerFan.setId(null);

	    return soccerFanRepository.save(soccerFan);
	}



	
	public List<SoccerFan> list(){
		return soccerFanRepository.findAll();
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
		//aplicar regras de negócio
		
		//0. verificar se existe
		//1. preechimento dos campos obrigatórios
		
		if(soccerFan == null) {
			throw new RuntimeException("Inform the soccer fan datas.");
		}
		
		if(soccerFan.getId() == null || soccerFan.getId() == 0L) {
			throw new RuntimeException("Inform the soccer fan identifier");
		}
		
		if(!this.soccerFanRepository.existsById(soccerFan.getId())) {
			throw new RuntimeException("The soccer fan with this ID doesn't exist: " + soccerFan.getId());
		}
		
		return this.soccerFanRepository.save(soccerFan);
		
	}
	
	
	
}
