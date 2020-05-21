package br.com.marvel.sistema.revista.mapper;

import br.com.marvel.sistema.criador.mapper.CriadorMapper;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.revista.model.RevistaEntity;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.marvel.sistema.revista.model.RevistaSaida;
import br.com.marvel.sistema.templates.*;
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
public class RevistaMapperTest {

    private RevistaMapper revistaMapper = Mappers.getMapper(RevistaMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_TEMPLATE_VALIDO);
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        List<HeroiEntity> listaHeroi = new ArrayList<>();
        listaHeroi.add(heroiEntity);


        //when
        RevistaEntity revistaEntity = revistaMapper.converterEntradaParaEntity(revistaEntrada,listaHeroi,criadorEntity);

        //then
        Assert.assertNotNull(revistaEntity);
        Assert.assertEquals(revistaEntrada.getNome(), revistaEntity.getNome());
        Assert.assertEquals(listaHeroi.get(0).getId(),revistaEntity.getListaHeroi().get(0).getId());
        Assert.assertEquals(listaHeroi.get(0).getListaPoder(),revistaEntity.getListaHeroi().get(0).getListaPoder());
        Assert.assertEquals(listaHeroi.get(0).getListaPoder().get(0).getId(),revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getId());
        Assert.assertEquals(listaHeroi.get(0).getListaPoder().get(0).getDescricao(),revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getId(),revistaEntity.getCriador().getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(),revistaEntity.getCriador().getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(),revistaEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(criadorEntity.getQtdConteudo(),revistaEntity.getCriador().getQtdConteudo());

    }

    @Test
    public void deveConverteEntityParaSaida(){
        //given
        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);

        //when
        RevistaSaida revistaSaida = revistaMapper.converterSaidaParaEntity(revistaEntity);

        //then
        Assert.assertEquals(revistaSaida.getId(), revistaEntity.getId());
        Assert.assertEquals(revistaSaida.getNome(), revistaEntity.getNome());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getNome(), revistaEntity.getListaHeroi().get(0).getNome());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getId(), revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getId());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getDescricao(), revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getDescricao());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getId(), revistaEntity.getListaHeroi().get(0).getCriador().getId());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getNomeCriador(), revistaEntity.getListaHeroi().get(0).getCriador().getNomeCriador());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getIdadeCriador(), revistaEntity.getListaHeroi().get(0).getCriador().getIdadeCriador());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getQtdConteudo(), revistaEntity.getListaHeroi().get(0).getCriador().getQtdConteudo());
        Assert.assertEquals(revistaSaida.getCriador().getId(), revistaEntity.getCriador().getId());
        Assert.assertEquals(revistaSaida.getCriador().getNomeCriador(), revistaEntity.getCriador().getNomeCriador());
        Assert.assertEquals(revistaSaida.getCriador().getIdadeCriador(), revistaEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(revistaSaida.getCriador().getQtdConteudo(), revistaEntity.getCriador().getQtdConteudo());

    }
}
