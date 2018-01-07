package com.igs.ipi.tpspringbootCHARTOIRE.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.igs.ipi.tpspringbootCHARTOIRE.Model.GameModel;
import com.igs.ipi.tpspringbootCHARTOIRE.Service.GameService;

@Controller
public class GameController {

	@Autowired
	private GameService monService;
	
	@GetMapping("game/new")
	public ModelAndView newGame()
	{
	 GameModel monModel =	monService.newGame();
		
		 ModelAndView modelAndView = new ModelAndView("game");
		 modelAndView.addObject("jeu", modelAndView);
		 return modelAndView;
	}
	
}
