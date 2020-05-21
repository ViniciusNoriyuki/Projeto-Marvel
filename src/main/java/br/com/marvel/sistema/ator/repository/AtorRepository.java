package br.com.marvel.sistema.ator.repository;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtorRepository extends JpaRepository<AtorEntity, Long> {
}

