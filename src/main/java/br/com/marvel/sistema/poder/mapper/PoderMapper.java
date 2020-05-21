package br.com.marvel.sistema.poder.mapper;


import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.model.PoderSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoderMapper {

    PoderMapper Instance = Mappers.getMapper(PoderMapper.class);

    PoderEntity converterEntradaParaEntity(PoderEntrada entrada);
    PoderSaida converterSaidaParaEntity(PoderEntity entidade);


}
