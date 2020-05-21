package br.com.marvel.sistema.ator.facade;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.ator.repository.AtorRepository;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.repository.HeroiRepository;
import br.com.marvel.sistema.templates.AtorEntityTemplate;
import br.com.marvel.sistema.templates.AtorEntradaTemplate;
import br.com.marvel.sistema.templates.HeroiEntityTemplate;
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
public class AtorFacadeTest {

    @InjectMocks
    AtorFacade atorFacade;

    @Mock
    AtorRepository atorRepository;

    @Mock
    HeroiRepository heroiRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarAtor() throws Exception {
        //given
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(heroiEntity));
        Mockito.when(atorRepository.save(Mockito.anyObject())).thenReturn(atorEntity);

        //when
        AtorSaida atorSaida = atorFacade.criarAtor(atorEntrada);

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

    @Test
    public void deveAlterarIdade() throws Exception {
        //given
        Long id = 1L;
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(atorEntity));
        Mockito.when(atorRepository.save(Mockito.anyObject())).thenReturn(atorEntity);

        //when
        AtorSaida atorSaida = atorFacade.alterarIdade(id, atorEntrada);

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAlterarIdade() throws Exception {
        //given
        Long id = null;
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);

        //when
        atorFacade.alterarIdade(id, atorEntrada);
    }

    @Test
    public void deveListarAtores(){
        //given
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        List<AtorEntity> listaAtorEntity = new ArrayList<>();
        listaAtorEntity.add(atorEntity);
        Mockito.when(atorRepository.findAll()).thenReturn(listaAtorEntity);

        //when
        List<AtorSaida> listaAtorSaida = atorFacade.listarAtores();

        //then
        Assert.assertNotNull(listaAtorSaida);
        Assert.assertEquals(atorEntity.getId(), listaAtorSaida.get(0).getId());
        Assert.assertEquals(atorEntity.getNomeAtor(), listaAtorSaida.get(0).getNomeAtor());
        Assert.assertEquals(atorEntity.getIdadeAtor(), listaAtorSaida.get(0).getIdadeAtor());
        Assert.assertEquals(atorEntity.getHeroi().getId(), listaAtorSaida.get(0).getHeroi().getId());
        Assert.assertEquals(atorEntity.getHeroi().getNome(), listaAtorSaida.get(0).getHeroi().getNome());
        Assert.assertEquals(atorEntity.getHeroi().getListaPoder().get(0).getId(), listaAtorSaida.get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(atorEntity.getHeroi().getListaPoder().get(0).getDescricao(), listaAtorSaida.get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getId(), listaAtorSaida.get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getNomeCriador(), listaAtorSaida.get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getIdadeCriador(), listaAtorSaida.get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(atorEntity.getHeroi().getCriador().getQtdConteudo(), listaAtorSaida.get(0).getHeroi().getCriador().getQtdConteudo());
    }

    @Test
    public void deveExcluirAtor() throws Exception {
        //given
        Long id = 1L;
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(atorEntity));

        //when
        AtorSaida atorSaida = atorFacade.excluirAtor(id);

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroExcluirAtor() throws Exception {
        //given
        Long id = null;
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);

        //when
        atorFacade.excluirAtor(id);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarCriarAtor() throws Exception {
        //given
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_INVALIDO);

        //when
        atorFacade.criarAtor(atorEntrada);
    }
}
