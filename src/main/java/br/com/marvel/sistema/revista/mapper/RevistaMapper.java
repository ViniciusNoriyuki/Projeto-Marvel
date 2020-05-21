package br.com.marvel.sistema.revista.mapper;


import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.revista.model.RevistaEntity;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.marvel.sistema.revista.model.RevistaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RevistaMapper {

    RevistaMapper Instance = Mappers.getMapper(RevistaMapper.class);

    @Mappings({
            @Mapping(target = "listaHeroi", source = "listaHeroi"),
            @Mapping(target = "criador", source = "criador"),
            @Mapping(target = "id", ignore = true)
    })

    RevistaEntity converterEntradaParaEntity(RevistaEntrada entrada, List<HeroiEntity> listaHeroi, CriadorEntity criador);
    RevistaSaida converterSaidaParaEntity(RevistaEntity entidade);
}
