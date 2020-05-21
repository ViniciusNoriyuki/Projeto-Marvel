package br.com.marvel.sistema.ator.model;

import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtorSaida {

    private Long id;
    private String nomeAtor;
    private Integer idadeAtor;
    private HeroiSaida heroi;

}
