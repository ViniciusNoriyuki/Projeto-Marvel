package br.com.marvel.sistema.revista.model;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
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
@Entity(name = "Revista")
public class RevistaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="nome")
	String nome;

	@ManyToMany
	List<HeroiEntity> listaHeroi;

	@OneToOne
	CriadorEntity criador;

}
