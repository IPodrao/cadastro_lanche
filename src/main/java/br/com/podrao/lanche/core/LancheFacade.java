package br.com.podrao.lanche.core;

import org.springframework.stereotype.Service;

import br.com.podrao.lanche.core.models.command.AlterarLancheCommand;
import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.ports.incoming.AlterarLanche;
import br.com.podrao.lanche.core.ports.incoming.ApagarLanche;
import br.com.podrao.lanche.core.ports.incoming.CadastrarLanche;
import br.com.podrao.lanche.core.ports.outoging.LancheDatabase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LancheFacade implements CadastrarLanche, ApagarLanche, AlterarLanche {

	private final LancheDatabase lancheDatabase;
	
	@Override
	public void executar(CadastrarLancheCommand command) {
		
		lancheDatabase.cadastrarLanche(command);
	}

	@Override
	public void executar(ApagarLancheCommand command) {

		lancheDatabase.apagarLanche(command);
	}

	@Override
	public void executar(AlterarLancheCommand command) {

		lancheDatabase.alterarLanche(command);
	}
}