package br.com.marvel.sistema.filme.facade;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.repository.AtorRepository;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import br.com.marvel.sistema.filme.repository.FilmeRepository;
import br.com.marvel.sistema.templates.AtorEntityTemplate;
import br.com.marvel.sistema.templates.CriadorEntityTemplate;
import br.com.marvel.sistema.templates.FilmeEntityTemplate;
import br.com.marvel.sistema.templates.FilmeEntradaTemplate;
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
public class FilmeFacadeTest {

    @InjectMocks
    FilmeFacade filmeFacade;

    @Mock
    FilmeRepository filmeRepository;

    @Mock
    CriadorRepository criadorRepository;

    @Mock
    AtorRepository atorRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarFilme() throws Exception {

        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.save(Mockito.anyObject())).thenReturn(filmeEntity);
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(atorEntity));
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));

        //when
        FilmeSaida filmeSaida = filmeFacade.criarFilme(filmeEntrada);

        //then
        Assert.assertNotNull(filmeSaida);
        Assert.assertEquals(filmeEntity.getId(), filmeSaida.getId());
        Assert.assertEquals(filmeEntity.getNomeFilme(), filmeSaida.getNomeFilme());
        Assert.assertEquals(filmeEntity.getDataLanc(), filmeSaida.getDataLanc());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getId(), filmeSaida.getAtores().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getIdadeAtor(), filmeSaida.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getId(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getNomeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(filmeEntity.getCriador().getId(), filmeSaida.getCriador().getId());
        Assert.assertEquals(filmeEntity.getCriador().getNomeCriador(), filmeSaida.getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getIdadeCriador(), filmeSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getQtdConteudo(), filmeSaida.getCriador().getQtdConteudo());
    }

    @Test
    public void deveListarFilmes(){

        //given
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);
        List<FilmeEntity> listaFilmes = new ArrayList<>();
        listaFilmes.add(filmeEntity);
        Mockito.when(filmeRepository.findAll()).thenReturn(listaFilmes);

        //when
        List<FilmeSaida> listaFilmeSaida = filmeFacade.listarFilmes();

        //then
        Assert.assertNotNull(listaFilmeSaida);
        Assert.assertEquals(filmeEntity.getId(), listaFilmeSaida.get(0).getId());
        Assert.assertEquals(filmeEntity.getNomeFilme(), listaFilmeSaida.get(0).getNomeFilme());
        Assert.assertEquals(filmeEntity.getDataLanc(), listaFilmeSaida.get(0).getDataLanc());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getId(), listaFilmeSaida.get(0).getAtores().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getNomeAtor(), listaFilmeSaida.get(0).getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getIdadeAtor(), listaFilmeSaida.get(0).getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getId(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getNome(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getId(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getNomeCriador(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), listaFilmeSaida.get(0).getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(filmeEntity.getCriador().getId(), listaFilmeSaida.get(0).getCriador().getId());
        Assert.assertEquals(filmeEntity.getCriador().getNomeCriador(), listaFilmeSaida.get(0).getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getIdadeCriador(), listaFilmeSaida.get(0).getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getQtdConteudo(), listaFilmeSaida.get(0).getCriador().getQtdConteudo());
    }

    @Test
    public void deveAlterarDataLancamento() throws Exception {
        //given
        Long id = 1L;
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));
        Mockito.when(filmeRepository.save(Mockito.anyObject())).thenReturn(filmeEntity);

        //when
        FilmeSaida filmeSaida = filmeFacade.alterarDataLancamento(id, filmeEntrada);

        //then
        Assert.assertNotNull(filmeSaida);
        Assert.assertEquals(filmeEntity.getId(), filmeSaida.getId());
        Assert.assertEquals(filmeEntity.getNomeFilme(), filmeSaida.getNomeFilme());
        Assert.assertEquals(filmeEntity.getDataLanc(), filmeSaida.getDataLanc());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getId(), filmeSaida.getAtores().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getIdadeAtor(), filmeSaida.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getId(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getNomeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(filmeEntity.getCriador().getId(), filmeSaida.getCriador().getId());
        Assert.assertEquals(filmeEntity.getCriador().getNomeCriador(), filmeSaida.getCriador().getNomeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getIdadeCriador(), filmeSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(filmeEntity.getCriador().getQtdConteudo(), filmeSaida.getCriador().getQtdConteudo());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAlterarDataLancamento() throws Exception {
        //given
        Long id = 1L;
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);

        //when
        filmeFacade.alterarDataLancamento(id, filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroListaDeAtores() throws Exception {
        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_INVALIDO);
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(atorEntity));

        //when
        filmeFacade.criarListaDeAtores(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroListaDeAtoresEmpity() throws Exception {
        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);

        //when
        filmeFacade.criarListaDeAtores(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAtribuirCriadorEmpity() throws Exception {
        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);

        //when
        filmeFacade.atribuirCriador(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAtribuirCriador() throws Exception {
        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_TEMPLATE_INVALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(criadorEntity));

        //when
        filmeFacade.atribuirCriador(filmeEntrada);
    }
}