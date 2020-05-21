package br.com.marvel.sistema.revista.controller;

import br.com.marvel.sistema.revista.facade.RevistaFacade;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.marvel.sistema.revista.model.RevistaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/revista", produces = "application/json")
@Configuration
@CrossOrigin
public class RevistaController {

	@Autowired
	public RevistaFacade revistaFacade;


	@PostMapping
	public RevistaSaida salvar(@RequestBody RevistaEntrada revistaEntrada) throws Exception {

		RevistaSaida saida = revistaFacade.buscarUsuario(revistaEntrada);
		return saida;
	}

	@GetMapping
	public List<RevistaSaida> listarRevistas(){
		List<RevistaSaida> saida = revistaFacade.listarRevistas();

		return saida;
	}
}