package br.com.marvel.sistema.ator.mapper;


import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AtorMapper {

    AtorMapper Instance = Mappers.getMapper(AtorMapper.class);

    @Mappings({
            @Mapping(source = "heroi", target = "heroi"),
            @Mapping(target = "id", ignore = true),
    })

    AtorEntity converterEntradaParaEntity(AtorEntrada entrada, HeroiEntity heroi);
    AtorSaida converterEntityParaSaida(AtorEntity entidade);


}
