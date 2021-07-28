package br.com.podrao.lanche.infra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.podrao.lanche.core.models.command.AlterarLancheCommand;
import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand.CadastrarLancheIngredientes;
import br.com.podrao.lanche.core.models.command.LancheDto;
import br.com.podrao.lanche.infra.entities.IngredienteEntity;
import br.com.podrao.lanche.infra.entities.LancheEntity;
import br.com.podrao.lanche.infra.repositories.LancheRepository;

@DataJpaTest
@DisplayName("Infra: Lanche")
class LancheAdapaterTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private LancheRepository lancheRepository;

	private LancheAdapter lancheAdapter;

	@BeforeEach
	void setup() {

		this.lancheAdapter = new LancheAdapter(lancheRepository);
	}

	@Test
	@DisplayName("Tenta cadastrar um lanche")
	void cadastrarLanche() {

		CadastrarLancheCommand command = new CadastrarLancheCommand("Cachorro quente", BigDecimal.valueOf(6l),
				List.of(new CadastrarLancheIngredientes("Pão", 1), new CadastrarLancheIngredientes("Salsicha", 1)), 1L);

		Long idLancheSalvo = lancheAdapter.cadastrarLanche(command);

		LancheEntity lancheEntity = testEntityManager.find(LancheEntity.class, idLancheSalvo);

		assertEquals(command.getNome(), lancheEntity.getNome());
		assertEquals(command.getValor(), lancheEntity.getValor());
		assertEquals(command.getIngredientes().size(), lancheEntity.getIngredientes().size());
	}

	@Test
	@DisplayName("Tenta apagar um lanche")
	void apagarLanche() {

		LancheEntity lancheEntity = new LancheEntity("Lanche 1", BigDecimal.valueOf(20), 1L)
				.adicionarIngrediente(new IngredienteEntity("Pão", 1))
				.adicionarIngrediente(new IngredienteEntity("Hamburguer", 1));

		LancheEntity persistAndFlush = testEntityManager.persistAndFlush(lancheEntity);
		testEntityManager.clear();

		ApagarLancheCommand command = new ApagarLancheCommand(persistAndFlush.getId());

		assertDoesNotThrow(() -> lancheAdapter.apagarLanche(command));
	}
	
	@Test
	@DisplayName("Tenta alterar um lanche")
	void alterarLanche() {
		
		LancheEntity persistAndFlush = testEntityManager.persistAndFlush(new LancheEntity("Lanche 1", BigDecimal.valueOf(20), 1L)
				.adicionarIngrediente(new IngredienteEntity("Pão", 1))
				.adicionarIngrediente(new IngredienteEntity("Hamburguer", 1)));
		testEntityManager.clear();
		
		AlterarLancheCommand command = new AlterarLancheCommand(persistAndFlush.getId(), "Novo nome", BigDecimal.valueOf(27L));
		
		LancheDto lancheAlterado = lancheAdapter.alterarLanche(command);
		
		assertEquals(command.getId(), lancheAlterado.getId());
		assertEquals(command.getNome(), lancheAlterado.getNome());
		assertEquals(command.getValor(), lancheAlterado.getValor());
	}
}
