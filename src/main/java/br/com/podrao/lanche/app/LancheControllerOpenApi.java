package br.com.podrao.lanche.app;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.podrao.lanche.app.models.AlterarLancheRequest;
import br.com.podrao.lanche.app.models.CadastrarLancheRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Lanche")
public interface LancheControllerOpenApi {

	@ApiOperation("Cadastrar lanche e ingredientes")
	public void cadastrarLanche(@RequestBody @Validated CadastrarLancheRequest request);
	
	@ApiOperation("Apagar lanche e ingredientes cadastrados")
	public void apagarLanche(@PathVariable Long id);
	
	@ApiOperation("Atualizar lanche")
	public void atualizarLanche(@PathVariable("id") Long id, @RequestBody @Validated AlterarLancheRequest request);
}
