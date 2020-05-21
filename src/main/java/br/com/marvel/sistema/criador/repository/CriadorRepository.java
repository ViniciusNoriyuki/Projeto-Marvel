package br.com.marvel.sistema.criador.repository;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriadorRepository extends JpaRepository<CriadorEntity, Long> {

}

