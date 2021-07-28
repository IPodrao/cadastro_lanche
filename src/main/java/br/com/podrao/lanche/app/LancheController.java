package br.com.podrao.lanche.app;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.podrao.lanche.app.mappers.AppLancheMapper;
import br.com.podrao.lanche.app.models.CadastrarLancheRequest;
import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;
import br.com.podrao.lanche.core.ports.incoming.ApagarLanche;
import br.com.podrao.lanche.core.ports.incoming.CadastrarLanche;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lanche")
public class LancheController {

	private final CadastrarLanche cadastraraLanche;
	private final ApagarLanche apagarLanche;

	private AppLancheMapper mapper = Mappers.getMapper(AppLancheMapper.class);

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrarLanche(@RequestBody @Validated CadastrarLancheRequest request) {

		cadastraraLanche.executar(mapper.converter(request));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void apagarLanche(@PathVariable Long id) {
		
		apagarLanche.executar(new ApagarLancheCommand(id));
	}
}