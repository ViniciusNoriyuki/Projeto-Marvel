package br.com.marvel.sistema.heroi.model;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.poder.model.PoderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Heroi")
public class HeroiEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="nome")
	String nome;

	@ManyToMany
	List<PoderEntity> listaPoder;

	@OneToOne
	CriadorEntity criador;
}
