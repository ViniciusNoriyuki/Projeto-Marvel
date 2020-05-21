package br.com.marvel.sistema.poder.controller;

import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.facade.PoderFacade;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.model.PoderSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/poder", produces = "application/json")
@Configuration
@CrossOrigin
public class PoderController {

	@Autowired
	public PoderFacade poderFacade;


	@PostMapping
	public PoderSaida salvar(@RequestBody PoderEntrada poderEntrada) {

		PoderSaida saida = poderFacade.buscarUsuario(poderEntrada);
		return saida;
	}

	@GetMapping
	public List<PoderSaida> listarPoderes(){
		List<PoderSaida> saida = poderFacade.listarPoderes();

		return saida;
	}
}