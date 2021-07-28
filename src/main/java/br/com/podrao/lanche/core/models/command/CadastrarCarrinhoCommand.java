package br.com.podrao.lanche.core.models.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CadastrarCarrinhoCommand {

	private Long id;
	private String nome;
}
