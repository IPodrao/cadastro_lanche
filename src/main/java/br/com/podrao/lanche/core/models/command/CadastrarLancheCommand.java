package br.com.podrao.lanche.core.models.command;

import java.math.BigDecimal;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CadastrarLancheCommand {

	private String nome;
	private BigDecimal valor;
	private Collection<CadastrarLancheIngredientes> ingredientes;
	
	@Getter
	@AllArgsConstructor
	public static class CadastrarLancheIngredientes {
		
		private String nome;
		private Integer quantidade;
	}
}
