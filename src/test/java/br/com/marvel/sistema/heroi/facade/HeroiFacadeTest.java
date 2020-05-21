package br.com.marvel.sistema.heroi.facade;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.heroi.repository.HeroiRepository;
import br.com.marvel.sistema.poder.facade.PoderFacade;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.templates.CriadorEntityTemplate;
import br.com.marvel.sistema.templates.HeroiEntityTemplate;
import br.com.marvel.sistema.templates.HeroiEntradaTemplate;
import br.com.marvel.sistema.templates.PoderEntityTemplate;
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
public class HeroiFacadeTest {

    @InjectMocks
    HeroiFacade heroiFacade;

    @Mock
    PoderFacade poderFacade;
    @Mock
    HeroiRepository heroiRepository;
    @Mock
    CriadorRepository criadorRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    };

    @Test
    public void devePreencherListaPoder() throws Exception {
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        Mockito.when(poderFacade.retornarPoderEntity(Mockito.anyLong())).thenReturn(poderEntity);

        //when
        List<PoderEntity> listaPoder = heroiFacade.preencherListaPoder(heroiEntrada);

        //then
        Assert.assertNotNull(listaPoder);
        Assert.assertEquals(listaPoder.get(0).getId(), poderEntity.getId());
        Assert.assertEquals(listaPoder.get(0).getDescricao(), poderEntity.getDescricao());
    }

    @Test
    public void deveVerificarQuantidadePoderes() throws Exception {
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        heroiFacade.verificarQuantidadePoderes(heroiEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroQuantidadePoderes() throws Exception {
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_INVALIDO);

        //when
        heroiFacade.verificarQuantidadePoderes(heroiEntrada);
    }

    @Test
    public void deveRetornarHeroiEntity() throws Exception {
        //given
        Long id = 1L;
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(heroiEntity));

        //when
        HeroiEntity heroiEntityResultado = heroiFacade.retornarHeroiEntity(id);

        //then
        Assert.assertNotNull(heroiEntityResultado);
        Assert.assertEquals(heroiEntityResultado.getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(heroiEntityResultado.getNome(), heroiEntity.getNome());
        Assert.assertEquals(heroiEntityResultado.getCriador().getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(heroiEntityResultado.getCriador().getNomeCriador(), heroiEntity.getCriador().getNomeCriador());
        Assert.assertEquals(heroiEntityResultado.getCriador().getIdadeCriador(), heroiEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(heroiEntityResultado.getCriador().getQtdConteudo(), heroiEntity.getCriador().getQtdConteudo());
        Assert.assertEquals(heroiEntityResultado.getListaPoder().get(0).getId(), heroiEntity.getListaPoder().get(0).getId());
        Assert.assertEquals(heroiEntityResultado.getListaPoder().get(0).getDescricao(), heroiEntity.getListaPoder().get(0).getDescricao());
    }

    @Test
    public void deveRetornarListaHeroi(){
        //given
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        List<HeroiEntity> listaHeroi = new ArrayList<>();
        listaHeroi.add(heroiEntity);
        Mockito.when(heroiRepository.findAll()).thenReturn(listaHeroi);

        //when
        List<HeroiSaida> listaHeroisSaida = heroiFacade.listarHerois();

        //then
        Assert.assertNotNull(listaHeroisSaida);
        Assert.assertEquals(listaHeroisSaida.get(0).getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(listaHeroisSaida.get(0).getNome(), heroiEntity.getNome());
        Assert.assertEquals(listaHeroisSaida.get(0).getCriador().getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(listaHeroisSaida.get(0).getCriador().getNomeCriador(), heroiEntity.getCriador().getNomeCriador());
        Assert.assertEquals(listaHeroisSaida.get(0).getCriador().getIdadeCriador(), heroiEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(listaHeroisSaida.get(0).getCriador().getQtdConteudo(), heroiEntity.getCriador().getQtdConteudo());
        Assert.assertEquals(listaHeroisSaida.get(0).getListaPoder().get(0).getId(), heroiEntity.getListaPoder().get(0).getId());
        Assert.assertEquals(listaHeroisSaida.get(0).getListaPoder().get(0).getDescricao(), heroiEntity.getListaPoder().get(0).getDescricao());
    }

    @Test
    public void deveCriarHeroi() throws Exception {
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));
        Mockito.when(poderFacade.retornarPoderEntity(Mockito.anyLong())).thenReturn(poderEntity);
        Mockito.when(heroiRepository.save(Mockito.anyObject())).thenReturn(heroiEntity);

        //when
        HeroiSaida heroiSaida = heroiFacade.criarHeroi(heroiEntrada);

        //then
        Assert.assertNotNull(heroiSaida);
        Assert.assertEquals(heroiSaida.getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(heroiSaida.getNome(), heroiEntity.getNome());
        Assert.assertEquals(heroiSaida.getCriador().getId(), heroiEntity.getCriador().getId());
        Assert.assertEquals(heroiSaida.getCriador().getNomeCriador(), heroiEntity.getCriador().getNomeCriador());
        Assert.assertEquals(heroiSaida.getCriador().getIdadeCriador(), heroiEntity.getCriador().getIdadeCriador());
        Assert.assertEquals(heroiSaida.getCriador().getQtdConteudo(), heroiEntity.getCriador().getQtdConteudo());
        Assert.assertEquals(heroiSaida.getListaPoder().get(0).getId(), heroiEntity.getListaPoder().get(0).getId());
        Assert.assertEquals(heroiSaida.getListaPoder().get(0).getDescricao(), heroiEntity.getListaPoder().get(0).getDescricao());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroQuantidadeConteudos() throws Exception {
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_INVALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_INVALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));

        //when
        heroiFacade.atribuirCriador(heroiEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroRetornoHeroiEntityEmpity() throws Exception {
        //given
        Long id = 1L;

        //when
        heroiFacade.retornarHeroiEntity(id);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAtribuirCriadorEmpity() throws Exception {
        //given
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        heroiFacade.atribuirCriador(heroiEntrada);
    }
}
