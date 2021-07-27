package br.com.podrao.lanche.core;

import org.springframework.stereotype.Service;

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.ports.incoming.CadastrarLanche;
import br.com.podrao.lanche.core.ports.outoging.LancheDatabase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LancheFacade implements CadastrarLanche {

	private final LancheDatabase lancheDatabase;
	
	@Override
	public void executar(CadastrarLancheCommand command) {
		
		lancheDatabase.cadastrarLanche(command);
	}
}