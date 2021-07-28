package br.com.podrao.lanche.core.models.command;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LancheDto {

	private Long id;
	private String nome;
	private BigDecimal valor;
}
