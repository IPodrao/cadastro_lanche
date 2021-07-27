package br.com.podrao.lanche.app.mappers;

import br.com.podrao.lanche.app.models.CadastrarLancheRequest;
import br.com.podrao.lanche.app.models.CadastrarLancheRequest.IngredienteRequest;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand.CadastrarLancheIngredientes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-27T18:17:13-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
public class AppLancheMapperImpl implements AppLancheMapper {

    @Override
    public CadastrarLancheCommand converter(CadastrarLancheRequest source) {
        if ( source == null ) {
            return null;
        }

        Collection<CadastrarLancheIngredientes> ingredientes = null;
        String nome = null;
        BigDecimal valor = null;

        ingredientes = ingredienteRequestCollectionToCadastrarLancheIngredientesCollection( source.getIngredientes() );
        nome = source.getNome();
        valor = source.getValor();

        CadastrarLancheCommand cadastrarLancheCommand = new CadastrarLancheCommand( nome, valor, ingredientes );

        return cadastrarLancheCommand;
    }

    protected CadastrarLancheIngredientes ingredienteRequestToCadastrarLancheIngredientes(IngredienteRequest ingredienteRequest) {
        if ( ingredienteRequest == null ) {
            return null;
        }

        String nome = null;
        Integer quantidade = null;

        nome = ingredienteRequest.getNome();
        quantidade = ingredienteRequest.getQuantidade();

        CadastrarLancheIngredientes cadastrarLancheIngredientes = new CadastrarLancheIngredientes( nome, quantidade );

        return cadastrarLancheIngredientes;
    }

    protected Collection<CadastrarLancheIngredientes> ingredienteRequestCollectionToCadastrarLancheIngredientesCollection(Collection<IngredienteRequest> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<CadastrarLancheIngredientes> collection1 = new ArrayList<CadastrarLancheIngredientes>( collection.size() );
        for ( IngredienteRequest ingredienteRequest : collection ) {
            collection1.add( ingredienteRequestToCadastrarLancheIngredientes( ingredienteRequest ) );
        }

        return collection1;
    }
}
