package com.rn.tec.footballapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rn.tec.footballapi.model.SoccerPlayer;
import com.rn.tec.footballapi.model.SoccerTeam;
import com.rn.tec.footballapi.repository.SoccerTeamRepository;

@Service
public class SoccerTeamService {
	
	@Autowired
	private SoccerTeamRepository soccerTeamRepository;
	
	public SoccerTeam include(SoccerTeam soccerTeam) {
		//aplicar regras de negócio
		//1. Preenchimento de campos obrigatórios
		
		soccerTeam.setId(null);
		
		return this.soccerTeamRepository.save(soccerTeam);
	}
	
	public List<SoccerTeam> list(){
		return this.soccerTeamRepository.findAll();
	}
	
	 public void exclude(Long id) {

	        if (id == null || id == 0l) {
	            throw new RuntimeException("Inform the soccer team identifier");
	        }

	        if (!this.soccerTeamRepository.existsById(id)) {
	            throw new RuntimeException("The soccer team with this ID doesn't exist:" + id);
	        }

	        this.soccerTeamRepository.deleteById(id);
	    }
	 
	 public SoccerTeam alter(SoccerTeam soccerTeam) {
			//aplicar regras de negócio
			
			//0. verificar se existe
			//1. preechimento dos campos obrigatórios
			
			if(soccerTeam == null) {
				throw new RuntimeException("Inform the soccer team datas.");
			}
			
			if(soccerTeam.getId() == null || soccerTeam.getId() == 0L) {
				throw new RuntimeException("Inform the soccer team identifier");
			}
			
			if(!this.soccerTeamRepository.existsById(soccerTeam.getId())) {
				throw new RuntimeException("The soccer team with this ID doesn't exist: " + soccerTeam.getId());
			}
			
			return this.soccerTeamRepository.save(soccerTeam);
			
		}
	
	
}
