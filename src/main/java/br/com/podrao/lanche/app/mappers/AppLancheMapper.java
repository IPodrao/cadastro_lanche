package br.com.podrao.lanche.app.mappers;

import org.mapstruct.Mapper;

import br.com.podrao.lanche.app.models.CadastrarLancheRequest;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;

@Mapper
public interface AppLancheMapper {

	CadastrarLancheCommand converter(CadastrarLancheRequest source);
}
