package br.com.marvel.sistema.filme.model;

import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeSaida {

    private Long id;
    private String nomeFilme;
    private String dataLanc;
    private List<AtorSaida> atores;
    private CriadorSaida criador;

}
