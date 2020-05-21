package br.com.marvel.sistema.revista.model;

import br.com.marvel.sistema.criador.model.CriadorSaida;
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
public class RevistaSaida {

    private Long id;
    private String nome;
    private List<HeroiSaida> listaHeroi;
    private CriadorSaida criador;

}
