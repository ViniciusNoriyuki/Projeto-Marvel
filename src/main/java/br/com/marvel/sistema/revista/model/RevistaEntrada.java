package br.com.marvel.sistema.revista.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevistaEntrada {

    String nome;
    List<Long> listaHeroi;
    Long criador;

}
