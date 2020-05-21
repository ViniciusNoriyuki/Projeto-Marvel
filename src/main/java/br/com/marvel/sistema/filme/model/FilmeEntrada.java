package br.com.marvel.sistema.filme.model;

import br.com.marvel.sistema.ator.model.AtorEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeEntrada {

    String nomeFilme;
    String dataLanc;
    List<Long> atores;
    Long criador;

}
