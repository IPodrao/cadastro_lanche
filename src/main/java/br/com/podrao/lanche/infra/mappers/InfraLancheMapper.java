package br.com.podrao.lanche.infra.mappers;

import org.mapstruct.Mapper;

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.models.command.LancheDto;
import br.com.podrao.lanche.infra.entities.LancheEntity;

@Mapper(componentModel = "spring")
public interface InfraLancheMapper {

	LancheEntity converter(CadastrarLancheCommand source);

	LancheDto converter(LancheEntity source);
}
