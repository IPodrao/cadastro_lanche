package br.com.podrao.lanche.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NaoExisteLancheComIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
}
