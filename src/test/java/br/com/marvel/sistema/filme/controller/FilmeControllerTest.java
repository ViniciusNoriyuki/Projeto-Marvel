package br.com.marvel.sistema.filme.controller;

import br.com.marvel.sistema.filme.facade.FilmeFacade;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import br.com.marvel.sistema.templates.FilmeEntradaTemplate;
import br.com.marvel.sistema.templates.FilmeSaidaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class FilmeControllerTest {

    @InjectMocks
    private FilmeController filmeController;

    @Mock
    private FilmeFacade filmeFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarFilme() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_TEMPLATE_VALIDO);
        Mockito.when(filmeFacade.criarFilme(Mockito.any(FilmeEntrada.class))).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(post("/marvel/filme")
                    .content(asJsonString(filmeEntrada))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

        String json = result.getResponse().getContentAsString();
        FilmeSaida saida = new ObjectMapper().readValue(json, FilmeSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeSaida.getId());
        Assert.assertEquals(saida.getNomeFilme(), filmeSaida.getNomeFilme());
        Assert.assertEquals(saida.getDataLanc(), filmeSaida.getDataLanc());
        Assert.assertEquals(saida.getAtores().get(0).getId(), filmeSaida.getAtores().get(0).getId());
        Assert.assertEquals(saida.getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(saida.getAtores().get(0).getIdadeAtor(), filmeSaida.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getId(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getNomeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(saida.getCriador().getId(), filmeSaida.getCriador().getId());
        Assert.assertEquals(saida.getCriador().getNomeCriador(), filmeSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.getCriador().getIdadeCriador(), filmeSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getCriador().getQtdConteudo(), filmeSaida.getCriador().getQtdConteudo());
    }

    @Test
    public void deveListarFilmes() throws Exception {
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_TEMPLATE_VALIDO);
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        List<FilmeSaida> listaFilmeSaida = new ArrayList<>();
        listaFilmeSaida.add(filmeSaida);
        Mockito.when(filmeFacade.listarFilmes()).thenReturn(listaFilmeSaida);

        MvcResult result = mockMvc.perform(get("/marvel/filme")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<FilmeSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, FilmeSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), filmeSaida.getId());
        Assert.assertEquals(saida.get(0).getNomeFilme(), filmeSaida.getNomeFilme());
        Assert.assertEquals(saida.get(0).getDataLanc(), filmeSaida.getDataLanc());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getId(), filmeSaida.getAtores().get(0).getId());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getIdadeAtor(), filmeSaida.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getCriador().getId(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getCriador().getNomeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.get(0).getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(saida.get(0).getCriador().getId(), filmeSaida.getCriador().getId());
        Assert.assertEquals(saida.get(0).getCriador().getNomeCriador(), filmeSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.get(0).getCriador().getIdadeCriador(), filmeSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.get(0).getCriador().getQtdConteudo(), filmeSaida.getCriador().getQtdConteudo());
    }

    @Test
    public void deveAlterarDataLancamento() throws Exception {
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_TEMPLATE_VALIDO);
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_TEMPLATE_VALIDO);
        Long id = 1L;
        Mockito.when(filmeFacade.alterarDataLancamento(Mockito.anyLong(),Mockito.any(FilmeEntrada.class))).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(patch("/marvel/filme/"+id+"/alterarDataLancamento")
                .content(asJsonString(filmeEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FilmeSaida saida = new ObjectMapper().readValue(json, FilmeSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeSaida.getId());
        Assert.assertEquals(saida.getNomeFilme(), filmeSaida.getNomeFilme());
        Assert.assertEquals(saida.getDataLanc(), filmeSaida.getDataLanc());
        Assert.assertEquals(saida.getAtores().get(0).getId(), filmeSaida.getAtores().get(0).getId());
        Assert.assertEquals(saida.getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(saida.getAtores().get(0).getIdadeAtor(), filmeSaida.getAtores().get(0).getIdadeAtor());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getId(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getId());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getNomeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo(), filmeSaida.getAtores().get(0).getHeroi().getCriador().getQtdConteudo());
        Assert.assertEquals(saida.getCriador().getId(), filmeSaida.getCriador().getId());
        Assert.assertEquals(saida.getCriador().getNomeCriador(), filmeSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.getCriador().getIdadeCriador(), filmeSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getCriador().getQtdConteudo(), filmeSaida.getCriador().getQtdConteudo());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
