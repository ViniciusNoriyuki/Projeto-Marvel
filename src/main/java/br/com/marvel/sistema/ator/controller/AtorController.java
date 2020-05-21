package br.com.marvel.sistema.ator.controller;

import br.com.marvel.sistema.ator.facade.AtorFacade;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.model.AtorSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/ator", produces = "application/json")
@Configuration
@CrossOrigin
public class AtorController {

	@Autowired
	public AtorFacade atorFacade;


	@PostMapping
	public AtorSaida salvar(@RequestBody AtorEntrada atorEntrada) throws Exception {

		AtorSaida saida = atorFacade.criarAtor(atorEntrada);
		return saida;
	}

	@GetMapping
	public List<AtorSaida> listarAtores() {

		List<AtorSaida> saida = atorFacade.listarAtores();

		return saida;
	}

	@PatchMapping("/{id}/alterarIdade")
	public AtorSaida alterarIdade(@PathVariable Long id, @RequestBody AtorEntrada atorEntrada) throws Exception {

		AtorSaida saida = atorFacade.alterarIdade(id, atorEntrada);

		return saida;
	}


	@DeleteMapping("/{id}/deletarAtor")
	public AtorSaida deletarUsuario(@PathVariable Long id) throws Exception {
		AtorSaida saida = atorFacade.excluirAtor(id);
		return saida;
	}

}