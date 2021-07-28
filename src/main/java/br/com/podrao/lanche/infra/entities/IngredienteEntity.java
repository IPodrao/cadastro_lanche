package br.com.podrao.lanche.infra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "ingrediente")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@ManyToOne(optional = false)
	private LancheEntity lanche;
	
	public IngredienteEntity(String nome, Integer quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public void definirLanche(LancheEntity lanche) {
		
		this.lanche = lanche;
	}
}