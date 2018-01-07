package com.igs.ipi.tpspringbootCHARTOIRE.Service;

import org.springframework.stereotype.Service;

import com.igs.ipi.tpspringbootCHARTOIRE.Model.GameModel;

@Service
public class GameService
{
	public GameModel newGame()
	{
		GameModel adver = new GameModel(null, null);
		String Name1 = "Raoul";
		String Name2 = "Bobby";
		adver.setName1(Name1);
		adver.setName2(Name2);
		
		return adver;
	}
}