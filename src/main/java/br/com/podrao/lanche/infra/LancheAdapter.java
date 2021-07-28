package br.com.podrao.lanche.infra;

import java.util.Optional;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.podrao.lanche.core.exceptions.NaoExisteLancheComIdException;
import br.com.podrao.lanche.core.models.command.AlterarLancheCommand;
import br.com.podrao.lanche.core.models.command.ApagarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.models.command.LancheDto;
import br.com.podrao.lanche.core.ports.outoging.LancheDatabase;
import br.com.podrao.lanche.infra.entities.LancheEntity;
import br.com.podrao.lanche.infra.mappers.InfraLancheMapper;
import br.com.podrao.lanche.infra.repositories.IngredienteRepository;
import br.com.podrao.lanche.infra.repositories.LancheRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LancheAdapter implements LancheDatabase {

	private final LancheRepository lancheRepository;
	private final IngredienteRepository ingredienteRepository;

	private InfraLancheMapper mapper = Mappers.getMapper(InfraLancheMapper.class);

	@Override
	@Transactional
	public Long cadastrarLanche(CadastrarLancheCommand command) {

		return lancheRepository.save(mapper.converter(command)).getId();
	}

	@Override
	@Transactional
	public void apagarLanche(ApagarLancheCommand command) {

		try {

			ingredienteRepository.deleteByLancheId(command.getId());
			lancheRepository.deleteById(command.getId());
		} catch (EmptyResultDataAccessException e) {

			throw new NaoExisteLancheComIdException(command.getId());
		}
	}

	@Override
	public LancheDto alterarLanche(AlterarLancheCommand command) {

		LancheEntity lanche = lancheRepository.findById(command.getId()).orElseThrow();
		
		Optional.ofNullable(command.getNome()).ifPresent(lanche::atualizarNome);
		Optional.ofNullable(command.getValor()).ifPresent(lanche::atualizarValor);
		
		return mapper.converter(lancheRepository.save(lanche));
	}



}
