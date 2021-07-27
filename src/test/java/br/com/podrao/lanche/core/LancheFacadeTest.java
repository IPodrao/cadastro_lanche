package br.com.podrao.lanche.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand.CadastrarLancheIngredientes;
import br.com.podrao.lanche.core.ports.outoging.LancheDatabase;

@DisplayName("Core: Lanche")
@ExtendWith(MockitoExtension.class)
class LancheFacadeTest {

	@Mock
	private LancheDatabase lancheDatabase;

	@InjectMocks
	private LancheFacade lancheFacade;

	@Test
	@DisplayName("Tenta cadastrar um lanche")
	void cadastrarLanche() {

		CadastrarLancheCommand command = new CadastrarLancheCommand("Cachorro quente", BigDecimal.valueOf(6l),
				List.of(new CadastrarLancheIngredientes("Pão", 1), new CadastrarLancheIngredientes("Salsicha", 1)));
		
		doReturn(1L).when(lancheDatabase).cadastrarLanche(any(CadastrarLancheCommand.class));

		assertDoesNotThrow(() -> lancheFacade.executar(command));

		verify(lancheDatabase, times(1)).cadastrarLanche(Mockito.any(CadastrarLancheCommand.class));
	}
}