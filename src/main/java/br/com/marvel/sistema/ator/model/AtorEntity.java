package br.com.marvel.sistema.ator.model;

import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.poder.model.PoderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Ator")
public class AtorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="nomeAtor")
	String nomeAtor;

	@Column(name="idadeAtor")
	Integer idadeAtor;

	@OneToOne
	HeroiEntity heroi;

}
