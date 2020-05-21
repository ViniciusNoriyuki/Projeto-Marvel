package br.com.marvel.sistema.filme.repository;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {

}

