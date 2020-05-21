package br.com.marvel.sistema.criador.controller;

import br.com.marvel.sistema.criador.facade.CriadorFacade;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/criador", produces = "application/json")
@Configuration
@CrossOrigin
public class CriadorController {

	@Autowired
	public CriadorFacade criadorFacade;


	@PostMapping
	public CriadorSaida salvar(@RequestBody CriadorEntrada criadorEntrada) {

		CriadorSaida saida = criadorFacade.buscarUsuario(criadorEntrada);
		return saida;
	}

	@PatchMapping("/{id}/alterarIdadeCriador")
	public CriadorSaida alterarIdade(@PathVariable Long id, @RequestBody CriadorEntrada criadorEntrada) throws Exception {

		CriadorSaida saida = criadorFacade.alterarIdade(id, criadorEntrada);

		return saida;
	}

	@GetMapping
	public List<CriadorSaida> listarUsuarios() {

		List<CriadorSaida> saida = criadorFacade.listarCriadores();

		return saida;
	}

}