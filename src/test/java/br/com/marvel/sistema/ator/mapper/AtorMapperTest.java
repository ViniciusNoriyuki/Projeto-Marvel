package br.com.marvel.sistema.ator.mapper;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.templates.AtorEntityTemplate;
import br.com.marvel.sistema.templates.AtorEntradaTemplate;
import br.com.marvel.sistema.templates.HeroiEntityTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AtorMapperTest {

    private AtorMapper atorMapper = Mappers.getMapper(AtorMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        AtorEntity atorEntity = atorMapper.converterEntradaParaEntity(atorEntrada, heroiEntity);

        //then
        Assert.assertNotNull(atorEntity);
        Assert.assertEquals(atorEntity.getNomeAtor(), atorEntrada.getNomeAtor());
        Assert.assertEquals(atorEntity.getIdadeAtor(), atorEntrada.getIdadeAtor());
        Assert.assertEquals(atorEntity.getHeroi().getId(), heroiEntity.getId());
        Assert.assertEquals(atorEntity.getHeroi().getNome(), heroiEntity.getNome());
        Assert.assertEquals(atorEntity.getHeroi().getListaPoder().get(0).getId(), heroiEntity.getListaPoder().get(0).getId());
        Assert.assertEquals(atorEntity.getHeroi().getListaPoder().get(0).getDescricao(), heroiEntity.getListaPoder().get(0).getDescricao());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getNomeCriador(), heroiEntity.getCriador().getNomeCriador());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getIdadeCriador(), heroiEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getQtdConteudo(), heroiEntity.getCriador().getQtdConteudo());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        //given
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);

        //when
        AtorSaida atorSaida = atorMapper.converterEntityParaSaida(atorEntity);

        //then
        Assert.assertNotNull(atorSaida);
        Assert.assertEquals(atorEntity.getId(), atorSaida.getId());
        Assert.assertEquals(atorEntity.getNomeAtor(), atorSaida.getNomeAtor());
        Assert.assertEquals(atorEntity.getIdadeAtor(), atorSaida.getIdadeAtor());
        Assert.assertEquals(atorEntity.getHeroi().getId(), atorSaida.getHeroi().getId());
        Assert.assertEquals(atorEntity.getHeroi().getNome(), atorSaida.getHeroi().getNome());
        Assert.assertEquals(atorEntity.getHeroi().getListaPoder().get(0).getId(), atorSaida.getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(atorEntity.getHeroi().getListaPoder().get(0).getDescricao(), atorSaida.getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getId(), atorSaida.getHeroi().getCriador().getId());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getNomeCriador(), atorSaida.getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getIdadeCriador(), atorSaida.getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getQtdConteudo(), atorSaida.getHeroi().getCriador().getQtdConteudo());
    }
}
