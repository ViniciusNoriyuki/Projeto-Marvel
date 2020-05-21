package br.com.marvel.sistema.filme.controller;

import br.com.marvel.sistema.filme.facade.FilmeFacade;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/filme", produces = "application/json")
@Configuration
@CrossOrigin
public class FilmeController {

	@Autowired
	public FilmeFacade filmeFacade;

	@PostMapping
	public FilmeSaida salvar(@RequestBody FilmeEntrada filmeEntrada) throws Exception {

		FilmeSaida saida = filmeFacade.criarFilme(filmeEntrada);
		return saida;
	}

	@PatchMapping("/{id}/alterarDataLancamento")
	public FilmeSaida alterarDataLancamento(@PathVariable Long id, @RequestBody FilmeEntrada filmeEntrada) throws Exception {

		FilmeSaida saida = filmeFacade.alterarDataLancamento(id, filmeEntrada);

		return saida;
	}

	@GetMapping
	public List<FilmeSaida> listarFilmes() {

		List<FilmeSaida> saida = filmeFacade.listarFilmes();

		return saida;
	}

}