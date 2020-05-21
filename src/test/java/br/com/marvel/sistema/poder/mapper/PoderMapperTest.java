package br.com.marvel.sistema.poder.mapper;

import br.com.marvel.sistema.criador.mapper.CriadorMapper;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.model.PoderSaida;
import br.com.marvel.sistema.templates.CriadorEntityTemplate;
import br.com.marvel.sistema.templates.CriadorEntradaTemplate;
import br.com.marvel.sistema.templates.PoderEntityTemplate;
import br.com.marvel.sistema.templates.PoderEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class PoderMapperTest {

    private PoderMapper poderMapper = Mappers.getMapper(PoderMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_TEMPLATE_VALIDO);

        //when
        PoderEntity poderEntity = poderMapper.converterEntradaParaEntity(poderEntrada);

        //then
        Assert.assertNotNull(poderEntity);
        Assert.assertEquals(poderEntrada.getDescricao(), poderEntity.getDescricao());
    }

    @Test
    public void deveConverteEntityParaSaida(){
        //given
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);

        //when
        PoderSaida poderSaida = poderMapper.converterSaidaParaEntity(poderEntity);

        //then
        Assert.assertNotNull(poderSaida);
        Assert.assertEquals(poderEntity.getId(), poderSaida.getId());
        Assert.assertEquals(poderEntity.getDescricao(), poderSaida.getDescricao());
    }
}
