package br.com.podrao.lanche.app.models;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
// Implementar validação condicional
public class AlterarLancheRequest {

	private String nome;
	
	private BigDecimal valor;
}
