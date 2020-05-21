package br.com.marvel.sistema.heroi.model;

import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.poder.model.PoderSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroiSaida {

    private Long id;
    private String nome;
    private List<PoderSaida> listaPoder;
    private CriadorSaida criador;

}
