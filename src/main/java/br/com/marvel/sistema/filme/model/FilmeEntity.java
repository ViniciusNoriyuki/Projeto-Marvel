package br.com.marvel.sistema.filme.model;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntity;
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
@Entity(name="Filme")
public class FilmeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="nomeFilme")
	String nomeFilme;

	@Column(name="dataLanc")
	String dataLanc;

	@ManyToMany
	List<AtorEntity> atores;

	@OneToOne
	CriadorEntity criador;

}