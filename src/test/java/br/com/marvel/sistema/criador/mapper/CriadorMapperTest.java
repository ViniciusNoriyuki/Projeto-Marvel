package br.com.marvel.sistema.criador.mapper;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.templates.CriadorEntityTemplate;
import br.com.marvel.sistema.templates.CriadorEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CriadorMapperTest {

    private CriadorMapper criadorMapper = Mappers.getMapper(CriadorMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);

        //when
        CriadorEntity criadorEntity = criadorMapper.converterEntradaParaEntity(criadorEntrada);

        //then
        Assert.assertNotNull(criadorEntity);
        Assert.assertEquals(criadorEntrada.getNomeCriador(), criadorEntity.getNomeCriador());
        Assert.assertEquals(criadorEntrada.getIdadeCriador(), criadorEntity.getIdadeCriador());
    }

    @Test
    public void deveConverteEntityParaSaida(){
        //given
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);

        //when
        CriadorSaida criadorSaida = criadorMapper.converterEntityParaSaida(criadorEntity);

        //then
        Assert.assertNotNull(criadorEntity);
        Assert.assertEquals(criadorEntity.getId(), criadorSaida.getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(), criadorSaida.getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(), criadorSaida.getIdadeCriador());
    }
}
