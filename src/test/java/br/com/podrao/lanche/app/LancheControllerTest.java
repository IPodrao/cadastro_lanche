package br.com.podrao.lanche.app;

import static org.mockito.Mockito.any;
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

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.ports.incoming.CadastrarLanche;
import br.com.podrao.lanche.util.JsonCreator;

@DisplayName("App: Lanche")
@WebMvcTest(LancheController.class)
class LancheControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CadastrarLanche cadastrarLanche;

	@Test
	@DisplayName("Tenta cadastrar um lanche")
	void cadastrarUmLanche() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post("/lanche").contentType(MediaType.APPLICATION_JSON)
				.content(JsonCreator.startJson()
						.name("nome").value("Enourmous Mac")
						.name("valor").value(23)
						.name("ingredientes").listValue(
								"[{\"nome\":\"Hamburguer\", \"quantidade\": 2},"
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
}