package br.com.marvel.sistema.ator.model;

import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtorEntrada {

    String nomeAtor;
    Integer idadeAtor;
    Long heroi;
}
