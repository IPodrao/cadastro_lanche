package br.com.podrao.lanche.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.podrao.lanche.core.models.command.AlterarLancheCommand;
import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.ports.incoming.AlterarLanche;
import br.com.podrao.lanche.core.ports.incoming.ApagarLanche;
import br.com.podrao.lanche.core.ports.incoming.CadastrarLanche;
import br.com.podrao.lanche.core.ports.incoming.ObterLanche;
import br.com.podrao.lanche.util.JsonCreator;

@DisplayName("App: Lanche")
@WebMvcTest(LancheController.class)
class LancheControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CadastrarLanche cadastrarLanche;

	@MockBean
	private ApagarLanche apagarLanche;

	@MockBean
	private ObterLanche obterLanche;
	
	@MockBean
	private AlterarLanche alterarLanche;

	@Test
	@DisplayName("Tenta cadastrar um lanche")
	void cadastrarUmLanche() throws Exception {

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/lanche").contentType(MediaType.APPLICATION_JSON)
						.content(JsonCreator.startJson().name("nome").value("Enourmous Mac").name("valor").value(23)
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

		verify(cadastrarLanche).executar(any(CadastrarLancheCommand.class));
	}

	@Test
	@DisplayName("Tenta apagar um lanche")
	void apagarUmLanche() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/lanche/1")).andExpect(status().isOk());

		verify(apagarLanche).executar(any(ApagarLancheCommand.class));
	}

	@Test
	@DisplayName("Tenta alterar lanche")
	void alterarLanche() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.put("/lanche/1").contentType(MediaType.APPLICATION_JSON).content(
				JsonCreator.startJson().name("nome").value("Super Enourmous Mac").name("valor").value(27).endJson()))
				.andExpect(status().isOk());
		
		verify(alterarLanche, times(1)).executar(any(AlterarLancheCommand.class));
	}
}