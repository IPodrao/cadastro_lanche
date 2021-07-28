package br.com.podrao.lanche.core.ports.incoming;

import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;

public interface ApagarLanche {

	void executar(ApagarLancheCommand command);

}
