package br.com.podrao.lanche.infra.entities;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.podrao.lanche.common.Default;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "lanche")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LancheEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Getter(AccessLevel.NONE)
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<IngredienteEntity> ingredientes;
	
	@Column(nullable = false)
	private Long carrinhoId;

	public LancheEntity(String nome, BigDecimal valor, Long carrinhoId) {
		this(nome, valor, carrinhoId, new HashSet<>());
	}
	
	@Default
	public LancheEntity(String nome, BigDecimal valor, Long carrinhoId, Set<IngredienteEntity> ingredientes) {
		this.nome = nome;
		this.valor = valor;
		this.carrinhoId = carrinhoId;
		this.ingredientes = ingredientes;
		
		ingredientes.forEach(ingrediente -> ingrediente.definirLanche(this));
	}
	
	public LancheEntity adicionarIngrediente(IngredienteEntity ingrediente) {
		
		this.ingredientes.add(ingrediente);
		ingrediente.definirLanche(this);
		return this;
	}
	
	public Collection<IngredienteEntity> getIngredientes() {
		
		return Collections.unmodifiableSet(this.ingredientes);
	}
	
	public void atualizarNome(String nome) {
		
		this.nome = nome;
	}
	
	public void atualizarValor(BigDecimal valor) {
		
		this.valor = valor;
	}
}