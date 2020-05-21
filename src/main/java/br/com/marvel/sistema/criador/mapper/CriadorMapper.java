package br.com.marvel.sistema.criador.mapper;


import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CriadorMapper {

    CriadorMapper Instance = Mappers.getMapper(CriadorMapper.class);

    CriadorEntity converterEntradaParaEntity(CriadorEntrada entrada);
    CriadorSaida converterEntityParaSaida(CriadorEntity entidade);


}
