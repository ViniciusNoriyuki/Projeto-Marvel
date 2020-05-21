package br.com.marvel.sistema.heroi.model;

import br.com.marvel.sistema.poder.mapper.PoderMapper;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroiEntrada {

    String nome;
    List<Long> listaPoder;
    Long criador;
}
