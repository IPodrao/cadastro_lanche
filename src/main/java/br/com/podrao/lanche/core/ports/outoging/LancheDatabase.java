package br.com.podrao.lanche.core.ports.outoging;

import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;

public interface LancheDatabase {

	Long cadastrarLanche(CadastrarLancheCommand command);

	void apagarLanche(ApagarLancheCommand command);

}
