package br.com.marvel.sistema.filme.mapper;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import br.com.marvel.sistema.templates.AtorEntityTemplate;
import br.com.marvel.sistema.templates.CriadorEntityTemplate;
import br.com.marvel.sistema.templates.FilmeEntityTemplate;
import br.com.marvel.sistema.templates.FilmeEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FilmeMapperTest {

    FilmeMapper filmeMapper = Mappers.getMapper(FilmeMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        List<AtorEntity> listaAtorEntity = new ArrayList<>();
        listaAtorEntity.add(atorEntity);

        //when
        FilmeEntity filmeEntity = filmeMapper.converterEntradaParaEntity(filmeEntrada, listaAtorEntity, criadorEntity);

        //then
        Assert.assertNotNull(filmeEntity);
        Assert.assertEquals(filmeEntrada.getNomeFilme(), filmeEntity.getNomeFilme());
        Assert.assertEquals(filmeEntrada.getDataLanc(), filmeEntity.getDataLanc());
        Assert.assertEquals(listaAtorEntity.get(0).getNomeAtor(), filmeEntity.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(listaAtorEntity.get(0).getIdadeAtor(), filmeEntity.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(listaAtorEntity.get(0).getHeroi().getId(), filmeEntity.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(listaAtorEntity.get(0).getHeroi().getNome(), filmeEntity.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(listaAtorEntity.get(0).getHeroi().getListaPoder().get(0).getId(), filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(listaAtorEntity.get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getId(), filmeEntity.getCriador().getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(), filmeEntity.getCriador().getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(), filmeEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(criadorEntity.getQtdConteudo(), filmeEntity.getCriador().getQtdConteudo());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        //given
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);

        //when
        FilmeSaida filmeSaida = filmeMapper.converterSaidaParaEntity(filmeEntity);

        //then
        Assert.assertNotNull(filmeSaida);
        Assert.assertEquals(filmeEntity.getId(), filmeSaida.getId());
        Assert.assertEquals(filmeEntity.getNomeFilme(), filmeSaida.getNomeFilme());
        Assert.assertEquals(filmeEntity.getDataLanc(), filmeSaida.getDataLanc());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getId(), filmeSaida.getAtores().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getIdadeAtor(), filmeSaida.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getId(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getNomeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(filmeEntity.getCriador().getId(), filmeSaida.getCriador().getId());
        Assert.assertEquals(filmeEntity.getCriador().getNomeCriador(), filmeSaida.getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getIdadeCriador(), filmeSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getQtdConteudo(), filmeSaida.getCriador().getQtdConteudo());
    }
}
