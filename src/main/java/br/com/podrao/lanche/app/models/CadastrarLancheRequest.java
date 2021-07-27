package br.com.podrao.lanche.app.models;

import java.math.BigDecimal;
import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CadastrarLancheRequest {

	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal valor;
	
	@NotEmpty
	private Collection<IngredienteRequest> ingredientes;
	
	@Getter
	public static class IngredienteRequest {
	
		@NotBlank
		private String nome;
		
		@NotBlank
		private Integer quantidade;
	}
}
