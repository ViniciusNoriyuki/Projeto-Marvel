package br.com.marvel.sistema.revista.repository;

import br.com.marvel.sistema.revista.model.RevistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevistaRepository extends JpaRepository<RevistaEntity, Long> {

}

