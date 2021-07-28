package br.com.podrao.lanche.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.podrao.lanche.infra.entities.IngredienteEntity;

public interface IngredienteRepository extends JpaRepository<IngredienteEntity, Long> {

	void deleteByLancheId(Long id); 
}
