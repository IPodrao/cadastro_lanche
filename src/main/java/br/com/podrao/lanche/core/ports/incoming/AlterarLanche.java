package br.com.podrao.lanche.core.ports.incoming;

import br.com.podrao.lanche.core.models.command.AlterarLancheCommand;

public interface AlterarLanche {

	void executar(AlterarLancheCommand command);

}
