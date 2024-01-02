package com.rn.tec.footballapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rn.tec.footballapi.model.SoccerPlayer;
import com.rn.tec.footballapi.repository.SoccerPlayerRepository;

@Service
public class SoccerPlayerService {
	
	@Autowired
	private SoccerPlayerRepository soccerPlayerRepository;
	
	
	public SoccerPlayer include(SoccerPlayer soccerPlayer) {
		//aplicar regras de negócio
		//1. Preenchimento de campos obrigatórios
		
		soccerPlayer.setId(null);
		
		return soccerPlayerRepository.save(soccerPlayer);
		
	}
	
	public List<SoccerPlayer> list(){
		return soccerPlayerRepository.findAll();
	}
	
	public void exclude(Long id) {
		if(id == null || id == 0L) {
			throw new RuntimeException("Soccer Player ID doesn't exist");
		}
		
		if(!this.soccerPlayerRepository.existsById(id)) {
			throw new RuntimeException("The soccer player with this ID doesn't exist: " + id);
		}
		
		this.soccerPlayerRepository.deleteById(id);
	}
	
	public SoccerPlayer alter(SoccerPlayer soccerPlayer) {
		//aplicar regras de negócio
		
		//0. verificar se existe
		//1. preechimento dos campos obrigatórios
		
		if(soccerPlayer == null) {
			throw new RuntimeException("Inform the soccer player datas.");
		}
		
		if(soccerPlayer.getId() == null || soccerPlayer.getId() == 0L) {
			throw new RuntimeException("Inform the soccer player identifier");
		}
		
		if(!this.soccerPlayerRepository.existsById(soccerPlayer.getId())) {
			throw new RuntimeException("The soccer player with this ID doesn't exist: " + soccerPlayer.getId());
		}
		
		return this.soccerPlayerRepository.save(soccerPlayer);
		
	}
	
	
	
}
