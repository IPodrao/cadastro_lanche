package br.com.podrao.lanche.infra;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.ports.outoging.LancheDatabase;
import br.com.podrao.lanche.infra.mappers.InfraLancheMapper;
import br.com.podrao.lanche.infra.repositories.LancheRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LancheAdapter implements LancheDatabase {

	private final LancheRepository lancheRepository;
	
	private InfraLancheMapper mapper = Mappers.getMapper(InfraLancheMapper.class);
	
	@Override
	@Transactional
	public Long cadastrarLanche(CadastrarLancheCommand command) {
		
		return lancheRepository.save(mapper.converter(command)).getId();
	}

}
