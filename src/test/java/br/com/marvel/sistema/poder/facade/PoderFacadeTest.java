package br.com.marvel.sistema.poder.facade;

import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.model.PoderSaida;
import br.com.marvel.sistema.poder.repository.PoderRepository;
import br.com.marvel.sistema.templates.HeroiEntityTemplate;
import br.com.marvel.sistema.templates.PoderEntityTemplate;
import br.com.marvel.sistema.templates.PoderEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PoderFacadeTest {

    @InjectMocks
    PoderFacade poderFacade;

    @Mock
    PoderRepository poderRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarPoder(){
        //given
        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_TEMPLATE_VALIDO);
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        Mockito.when(poderRepository.save(Mockito.anyObject())).thenReturn(poderEntity);

        //when
        PoderSaida poderSaida = poderFacade.buscarUsuario(poderEntrada);

        //then
        Assert.assertNotNull(poderSaida);
        Assert.assertEquals(poderEntity.getId(), poderSaida.getId());
        Assert.assertEquals(poderEntity.getDescricao(), poderSaida.getDescricao());
    }

    @Test
    public void deveRetornarListaPoder(){
        //given
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        List<PoderEntity> listaPoder = new ArrayList<>();
        listaPoder.add(poderEntity);
        Mockito.when(poderRepository.findAll()).thenReturn(listaPoder);

        //when
        List<PoderSaida> listaPoderSaida = poderFacade.listarPoderes();

        //then
        Assert.assertNotNull(listaPoderSaida);
        Assert.assertEquals(poderEntity.getId(), listaPoderSaida.get(0).getId());
        Assert.assertEquals(poderEntity.getDescricao(), listaPoderSaida.get(0).getDescricao());
    }

    @Test
    public void deveRetornarPoderEntity() throws Exception {
        //given
        Long id = 1L;
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        Mockito.when(poderRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(poderEntity));

        //when
        PoderEntity resultado = poderFacade.retornarPoderEntity(id);

        //then
        Assert.assertNotNull(resultado);
        Assert.assertEquals(poderEntity.getId(), resultado.getId());
        Assert.assertEquals(poderEntity.getDescricao(), resultado.getDescricao());

    }

    @Test(expected = java.lang.Exception.class)
    public void deveRetornarErroPoderEntity() throws Exception {
        //given
        Long id = 1L;

        //when
        poderFacade.retornarPoderEntity(id);
    }
}
