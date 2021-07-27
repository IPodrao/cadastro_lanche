package br.com.podrao.lanche.infra.mappers;

import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand;
import br.com.podrao.lanche.core.models.command.CadastrarLancheCommand.CadastrarLancheIngredientes;
import br.com.podrao.lanche.infra.entities.IngredienteEntity;
import br.com.podrao.lanche.infra.entities.LancheEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-27T18:17:13-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
public class InfraLancheMapperImpl implements InfraLancheMapper {

    @Override
    public LancheEntity converter(CadastrarLancheCommand source) {
        if ( source == null ) {
            return null;
        }

        String nome = null;
        BigDecimal valor = null;

        nome = source.getNome();
        valor = source.getValor();

        LancheEntity lancheEntity = new LancheEntity( nome, valor );

        if ( lancheEntity.getIngredientes() != null ) {
            List<IngredienteEntity> list = cadastrarLancheIngredientesCollectionToIngredienteEntityList( source.getIngredientes() );
            if ( list != null ) {
                lancheEntity.getIngredientes().addAll( list );
            }
        }

        return lancheEntity;
    }

    protected IngredienteEntity cadastrarLancheIngredientesToIngredienteEntity(CadastrarLancheIngredientes cadastrarLancheIngredientes) {
        if ( cadastrarLancheIngredientes == null ) {
            return null;
        }

        String nome = null;
        Integer quantidade = null;

        nome = cadastrarLancheIngredientes.getNome();
        quantidade = cadastrarLancheIngredientes.getQuantidade();

        IngredienteEntity ingredienteEntity = new IngredienteEntity( nome, quantidade );

        return ingredienteEntity;
    }

    protected List<IngredienteEntity> cadastrarLancheIngredientesCollectionToIngredienteEntityList(Collection<CadastrarLancheIngredientes> collection) {
        if ( collection == null ) {
            return null;
        }

        List<IngredienteEntity> list = new ArrayList<IngredienteEntity>( collection.size() );
        for ( CadastrarLancheIngredientes cadastrarLancheIngredientes : collection ) {
            list.add( cadastrarLancheIngredientesToIngredienteEntity( cadastrarLancheIngredientes ) );
        }

        return list;
    }
}
