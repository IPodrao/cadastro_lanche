package br.com.podrao.lanche.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.podrao.lanche.infra.entities.LancheEntity;

public interface LancheRepository extends JpaRepository<LancheEntity, Long> {

}
