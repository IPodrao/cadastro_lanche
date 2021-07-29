package br.com.podrao.lanche.integacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.podrao.lanche.infra.entities.LancheEntity;
import br.com.podrao.lanche.infra.repositories.LancheRepository;
import br.com.podrao.lanche.util.CustomPostgresContainer;
import br.com.podrao.lanche.util.JsonCreator;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ContextConfiguration
@SuppressWarnings("rawtypes")
@TestPropertySource(properties = {
	    "app.description=descricao",
	    "app.name=nome",
	    "app.version=versão"
	})
class IntegracaoBancoDeDadosTest {

	@Container
	private static final PostgreSQLContainer CONTAINER = CustomPostgresContainer.getInstance();

	@Autowired
	private LancheRepository lancheRepository;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {

	}

	@Test
	@DisplayName("Tenta cadastrar, alterar e apagar lanches")
	void cadastrarAlterarEApagarLanche() throws Exception {

		final String NOME = "Enourmous Mac";
		final BigDecimal VALOR = BigDecimal.valueOf(23);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/lanche").contentType(MediaType.APPLICATION_JSON)
						.content(JsonCreator.startJson().name("nome").value(NOME).name("valor").value(VALOR.intValue())
								.name("carrinhoId").value(1).name("ingredientes")
								.listValue("[{\"nome\":\"Hamburguer\", \"quantidade\": 2},"
										+ "{\"nome\":\"Alface\", \"quantidade\": 1},"
										+ "{\"nome\":\"Queijo\", \"quantidade\": 1},"
										+ "{\"nome\":\"Molho especial\", \"quantidade\": 1},"
										+ "{\"nome\":\"Cebola\", \"quantidade\": 1},"
										+ "{\"nome\":\"Picles\", \"quantidade\": 1},"
										+ "{\"nome\":\"Pão com Gergilin\", \"quantidade\": 1}]")
								.endJson()))
				.andExpect(status().isCreated());

		List<LancheEntity> lanches = lancheRepository.findAll();
		assertEquals(1, lanches.size());
		LancheEntity lanche = lanches.iterator().next();

		assertEquals(NOME, lanche.getNome());
		assertEquals(0, VALOR.compareTo(lanche.getValor()));

		final String NOVO_NOME = "Super Enourmous Mac";
		final BigDecimal NOVO_VALOR = BigDecimal.valueOf(27);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/lanche/" + lanche.getId())
				.contentType(MediaType.APPLICATION_JSON).content(JsonCreator.startJson().name("nome").value(NOVO_NOME)
						.name("valor").value(NOVO_VALOR.intValue()).endJson()))
				.andExpect(status().isOk());

		lanches = lancheRepository.findAll();
		assertEquals(1, lanches.size());
		lanche = lanches.iterator().next();

		assertEquals(NOVO_NOME, lanche.getNome());
		assertEquals(0, NOVO_VALOR.compareTo(lanche.getValor()));

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/lanche/" + lanche.getId())).andExpect(status().isOk());
		
		lanches = lancheRepository.findAll();
		assertEquals(0, lanches.size());
	}
}
