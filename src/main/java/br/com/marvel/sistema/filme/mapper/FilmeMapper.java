package br.com.marvel.sistema.filme.mapper;


import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

@Mapper
public interface FilmeMapper {

    FilmeMapper Instance = Mappers.getMapper(FilmeMapper.class);

    @Mappings({
            @Mapping(source = "atores", target = "atores"),
            @Mapping(source = "criador", target = "criador"),
            @Mapping(target = "id", ignore = true),
    })

    FilmeEntity converterEntradaParaEntity(FilmeEntrada entrada, List<AtorEntity> atores, CriadorEntity criador);
    FilmeSaida converterSaidaParaEntity(FilmeEntity entidade);

}
