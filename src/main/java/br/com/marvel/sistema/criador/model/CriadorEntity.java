package br.com.marvel.sistema.criador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Criador")
public class CriadorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="nome")
	String nomeCriador;

	@Column(name="idade")
	Integer idadeCriador;

	@Column(name = "qtdConteudo")
	Integer qtdConteudo;

}
