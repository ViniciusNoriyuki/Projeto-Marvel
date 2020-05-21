package br.com.marvel.sistema.heroi.mapper;


import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.model.PoderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HeroiMapper {

    HeroiMapper Instance = Mappers.getMapper(HeroiMapper.class);

    @Mappings({
            @Mapping(target = "listaPoder", source = "listaPoder"),
            @Mapping(target = "criador", source = "criador"),
            @Mapping(target = "id", ignore = true)
    })
    HeroiEntity converterEntradaParaEntity (HeroiEntrada entrada, List<PoderEntity> listaPoder, CriadorEntity criador);
    HeroiSaida converterSaidaParaEntity (HeroiEntity entidade);

}
