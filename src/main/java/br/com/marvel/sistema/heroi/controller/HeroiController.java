package br.com.marvel.sistema.heroi.controller;

import br.com.marvel.sistema.heroi.facade.HeroiFacade;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.bcel.internal.generic.DRETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/heroi", produces = "application/json")
@Configuration
@CrossOrigin
public class HeroiController {

	@Autowired
	public HeroiFacade heroiFacade;


	@PostMapping
	public HeroiSaida salvar(@RequestBody HeroiEntrada heroiEntrada) throws Exception {

		HeroiSaida saida = heroiFacade.criarHeroi(heroiEntrada);
		return saida;
	}

	@GetMapping
	public List<HeroiSaida> listarHerois(){
		List<HeroiSaida> saida = heroiFacade.listarHerois();

		return saida;
	}
}