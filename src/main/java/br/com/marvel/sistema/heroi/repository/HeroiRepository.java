package br.com.marvel.sistema.heroi.repository;

import br.com.marvel.sistema.heroi.model.HeroiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroiRepository extends JpaRepository<HeroiEntity, Long> {

}

