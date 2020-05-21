package br.com.marvel.sistema.criador.facade;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.templates.CriadorEntityTemplate;
import br.com.marvel.sistema.templates.CriadorEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class CriadorFacadeTest {

    @InjectMocks
    CriadorFacade criadorFacade;

    @Mock
    CriadorRepository criadorRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarCriador(){

        //given
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        Mockito.when(criadorRepository.save(Mockito.anyObject())).thenReturn(criadorEntity);

        //when
        CriadorSaida criadorSaida = criadorFacade.buscarUsuario(criadorEntrada);

        //then
        Assert.assertNotNull(criadorSaida);
        Assert.assertEquals(criadorEntity.getId(), criadorSaida.getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(), criadorSaida.getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(), criadorSaida.getIdadeCriador());
        Assert.assertEquals(criadorEntity.getQtdConteudo(), criadorSaida.getQtdConteudo());
    }

    @Test
    public void deveListarCriadores(){

        //given
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        List<CriadorEntity> listaCriadores = new ArrayList();
        listaCriadores.add(criadorEntity);
        Mockito.when(criadorRepository.findAll()).thenReturn(listaCriadores);

        //when
        List<CriadorSaida> listaCriadoresSaida = criadorFacade.listarCriadores();

        //then
        Assert.assertNotNull(listaCriadoresSaida);
        Assert.assertEquals(criadorEntity.getId(), listaCriadoresSaida.get(0).getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(), listaCriadoresSaida.get(0).getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(), listaCriadoresSaida.get(0).getIdadeCriador());
        Assert.assertEquals(criadorEntity.getQtdConteudo(), listaCriadoresSaida.get(0).getQtdConteudo());
    }

    @Test
    public void deveAlterarIdade() throws Exception {

        //given
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        Long idCriador = criadorEntity.getId();
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));
        Mockito.when(criadorRepository.save(Mockito.anyObject())).thenReturn(criadorEntity);

        //when
        CriadorSaida criadorSaida = criadorFacade.alterarIdade(idCriador,criadorEntrada);

        //then
        Assert.assertNotNull(criadorSaida);
        Assert.assertEquals(criadorEntity.getId(), criadorSaida.getId());
        Assert.assertEquals(criadorEntity.getNomeCriador(), criadorSaida.getNomeCriador());
        Assert.assertEquals(criadorEntity.getIdadeCriador(), criadorSaida.getIdadeCriador());
        Assert.assertEquals(criadorEntity.getQtdConteudo(), criadorSaida.getQtdConteudo());
    }

    @Test (expected = Exception.class)
    public void deveRetornarExceptionQuandoAlterarIdade() throws Exception {

        //given
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        Long idCriador = criadorEntity.getId();
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        Mockito.when(criadorRepository.save(Mockito.anyObject())).thenReturn(criadorEntity);

        //when
        CriadorSaida criadorSaida = criadorFacade.alterarIdade(idCriador,criadorEntrada);
    }
}