package br.com.podrao.lanche.core.ports.incoming;

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;

public interface CadastrarLanche {

	void executar(CadastrarLancheCommand command);
}
