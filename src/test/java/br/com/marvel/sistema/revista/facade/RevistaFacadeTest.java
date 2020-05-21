package br.com.marvel.sistema.revista.facade;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.heroi.facade.HeroiFacade;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.revista.model.RevistaEntity;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.marvel.sistema.revista.model.RevistaSaida;
import br.com.marvel.sistema.revista.repository.RevistaRepository;
import br.com.marvel.sistema.templates.*;
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
public class RevistaFacadeTest {

    @InjectMocks
    RevistaFacade revistaFacade;

    @Mock
    RevistaRepository revistaRepository;
    @Mock
    CriadorRepository criadorRepository;
    @Mock
    HeroiFacade heroiFacade;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarRevista() throws Exception {
        //given
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));
        Mockito.when(heroiFacade.retornarHeroiEntity(Mockito.anyObject())).thenReturn(heroiEntity);
        Mockito.when(revistaRepository.save(Mockito.anyObject())).thenReturn(revistaEntity);

        //when
        RevistaSaida revistaSaida = revistaFacade.buscarUsuario(revistaEntrada);

        //then
        Assert.assertNotNull(revistaSaida);
        Assert.assertEquals(revistaSaida.getId(), revistaEntity.getId());
        Assert.assertEquals(revistaSaida.getNome(), revistaEntity.getNome());
        Assert.assertEquals(revistaSaida.getCriador().getId(), revistaEntity.getCriador().getId());
        Assert.assertEquals(revistaSaida.getCriador().getNomeCriador(), revistaEntity.getCriador().getNomeCriador());
        Assert.assertEquals(revistaSaida.getCriador().getIdadeCriador(), revistaEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(revistaSaida.getCriador().getQtdConteudo(), revistaEntity.getCriador().getQtdConteudo());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getId(), revistaEntity.getListaHeroi().get(0).getId());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getNome(), revistaEntity.getListaHeroi().get(0).getNome());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getId(), revistaEntity.getListaHeroi().get(0).getCriador().getId());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getNomeCriador(), revistaEntity.getListaHeroi().get(0).getCriador().getNomeCriador());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getIdadeCriador(), revistaEntity.getListaHeroi().get(0).getCriador().getIdadeCriador());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getCriador().getQtdConteudo(), revistaEntity.getListaHeroi().get(0).getCriador().getQtdConteudo());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getId(), revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getId());
        Assert.assertEquals(revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getDescricao(), revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getDescricao());
    }

    @Test
    public void deveRetornarListaRevistas(){
        //given
        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);
        List<RevistaEntity> listaRevistas = new ArrayList<>();
        listaRevistas.add(revistaEntity);
        Mockito.when(revistaRepository.findAll()).thenReturn(listaRevistas);

        //when
        List<RevistaSaida> listaRevistasSaida = revistaFacade.listarRevistas();

        //then
        Assert.assertNotNull(listaRevistasSaida);
        Assert.assertEquals(listaRevistasSaida.get(0).getId(), revistaEntity.getId());
        Assert.assertEquals(listaRevistasSaida.get(0).getNome(), revistaEntity.getNome());
        Assert.assertEquals(listaRevistasSaida.get(0).getCriador().getId(), revistaEntity.getCriador().getId());
        Assert.assertEquals(listaRevistasSaida.get(0).getCriador().getNomeCriador(), revistaEntity.getCriador().getNomeCriador());
        Assert.assertEquals(listaRevistasSaida.get(0).getCriador().getIdadeCriador(), revistaEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(listaRevistasSaida.get(0).getCriador().getQtdConteudo(), revistaEntity.getCriador().getQtdConteudo());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getId(), revistaEntity.getListaHeroi().get(0).getId());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getNome(), revistaEntity.getListaHeroi().get(0).getNome());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getCriador().getId(), revistaEntity.getListaHeroi().get(0).getCriador().getId());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getCriador().getNomeCriador(), revistaEntity.getListaHeroi().get(0).getCriador().getNomeCriador());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getCriador().getIdadeCriador(), revistaEntity.getListaHeroi().get(0).getCriador().getIdadeCriador());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getCriador().getQtdConteudo(), revistaEntity.getListaHeroi().get(0).getCriador().getQtdConteudo());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getListaPoder().get(0).getId(), revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getId());
        Assert.assertEquals(listaRevistasSaida.get(0).getListaHeroi().get(0).getListaPoder().get(0).getDescricao(), revistaEntity.getListaHeroi().get(0).getListaPoder().get(0).getDescricao());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAtribuirCriadorConteudo() throws Exception {
        //given
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_INVALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));

        //when
        revistaFacade.atribuirCriador(revistaEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAtribuirCriadorEmpity() throws Exception {
        //given
        Long id = 1L;
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_TEMPLATE_VALIDO);

        //when
        revistaFacade.atribuirCriador(revistaEntrada);
    }
}
