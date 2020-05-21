package br.com.marvel.sistema.heroi.mapper;

import br.com.marvel.sistema.criador.mapper.CriadorMapper;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.model.PoderEntity;
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
public class HeroiMapperTest {

    private HeroiMapper heroiMapper = Mappers.getMapper(HeroiMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        List<PoderEntity> listaPoderEntity = new ArrayList();
        listaPoderEntity.add(poderEntity);

        //when
        HeroiEntity heroiEntity = heroiMapper.converterEntradaParaEntity(heroiEntrada,listaPoderEntity,criadorEntity);

        //then
        Assert.assertNotNull(heroiEntity);
        Assert.assertEquals(heroiEntrada.getNome(), heroiEntity.getNome());
        Assert.assertEquals(listaPoderEntity.get(0).getId(),heroiEntity.getListaPoder().get(0).getId());
        Assert.assertEquals(listaPoderEntity.get(0).getDescricao(),heroiEntity.getListaPoder().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getId(),heroiEntity.getCriador().getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(),heroiEntity.getCriador().getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(),heroiEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(criadorEntity.getQtdConteudo(),heroiEntity.getCriador().getQtdConteudo());
    }

    @Test
    public void deveConverteEntityParaSaida(){
        //given
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        HeroiSaida heroiSaida = heroiMapper.converterSaidaParaEntity(heroiEntity);

        //then
        Assert.assertNotNull(heroiSaida);
        Assert.assertEquals(heroiSaida.getId(), heroiEntity.getId());
        Assert.assertEquals(heroiSaida.getNome(), heroiEntity.getNome());
        Assert.assertEquals(heroiSaida.getListaPoder().get(0).getId(), heroiEntity.getListaPoder().get(0).getId());
        Assert.assertEquals(heroiSaida.getListaPoder().get(0).getDescricao(), heroiEntity.getListaPoder().get(0).getDescricao());
        Assert.assertEquals(heroiSaida.getCriador().getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(heroiSaida.getCriador().getNomeCriador(), heroiEntity.getCriador().getNomeCriador());
        Assert.assertEquals(heroiSaida.getCriador().getIdadeCriador(), heroiEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(heroiSaida.getCriador().getQtdConteudo(), heroiEntity.getCriador().getQtdConteudo());


    }
}
