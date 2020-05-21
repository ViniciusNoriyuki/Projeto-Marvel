package br.com.marvel.sistema.revista.controller;

import br.com.marvel.sistema.heroi.controller.HeroiController;
import br.com.marvel.sistema.heroi.facade.HeroiFacade;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.revista.facade.RevistaFacade;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.marvel.sistema.revista.model.RevistaSaida;
import br.com.marvel.sistema.templates.HeroiEntradaTemplate;
import br.com.marvel.sistema.templates.HeroiSaidaTemplate;
import br.com.marvel.sistema.templates.RevistaEntradaTemplate;
import br.com.marvel.sistema.templates.RevistaSaidaTemplate;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class RevistaControllerTest {

    @InjectMocks
    private RevistaController revistaController;

    @Mock
    private RevistaFacade revistaFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(revistaController).build();
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarRevista() throws Exception {
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_TEMPLATE_VALIDO);
        RevistaSaida revistaSaida = Fixture.from(RevistaSaida.class).gimme(RevistaSaidaTemplate.REVISTA_TEMPLATE_VALIDO);
        Mockito.when(revistaFacade.buscarUsuario(Mockito.any(RevistaEntrada.class))).thenReturn(revistaSaida);

        MvcResult result = mockMvc.perform(post("/marvel/revista")
                    .content(asJsonString(revistaEntrada))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

        String json = result.getResponse().getContentAsString();
        RevistaSaida saida = new ObjectMapper().readValue(json, RevistaSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), revistaSaida.getId());
        Assert.assertEquals(saida.getNome(), revistaEntrada.getNome());
        Assert.assertEquals(saida.getCriador().getId(), revistaSaida.getCriador().getId());
        Assert.assertEquals(saida.getCriador().getNomeCriador(), revistaSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.getCriador().getIdadeCriador(), revistaSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getCriador().getQtdConteudo(), revistaSaida.getCriador().getQtdConteudo());
        Assert.assertEquals(saida.getListaHeroi().get(0).getId(), revistaSaida.getListaHeroi().get(0).getId());
        Assert.assertEquals(saida.getListaHeroi().get(0).getNome(), revistaSaida.getListaHeroi().get(0).getNome());
        Assert.assertEquals(saida.getListaHeroi().get(0).getListaPoder().get(0).getId(), revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getListaHeroi().get(0).getListaPoder().get(0).getDescricao(), revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.getListaHeroi().get(0).getCriador().getId(), revistaSaida.getListaHeroi().get(0).getCriador().getId());
        Assert.assertEquals(saida.getListaHeroi().get(0).getCriador().getNomeCriador(), revistaSaida.getListaHeroi().get(0).getCriador().getNomeCriador());
        Assert.assertEquals(saida.getListaHeroi().get(0).getCriador().getIdadeCriador(), revistaSaida.getListaHeroi().get(0).getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getListaHeroi().get(0).getCriador().getQtdConteudo(), revistaSaida.getListaHeroi().get(0).getCriador().getQtdConteudo());
    }

    @Test
    public void deveListarHerois() throws Exception {
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_TEMPLATE_VALIDO);
        RevistaSaida revistaSaida = Fixture.from(RevistaSaida.class).gimme(RevistaSaidaTemplate.REVISTA_TEMPLATE_VALIDO);
        Long id = 1L;
        List<RevistaSaida> listaRevistaSaida = new ArrayList<>();
        listaRevistaSaida.add(revistaSaida);
        Mockito.when(revistaFacade.listarRevistas()).thenReturn(listaRevistaSaida);

        MvcResult result = mockMvc.perform(get("/marvel/revista/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<RevistaSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, RevistaSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), revistaSaida.getId());
        Assert.assertEquals(saida.get(0).getNome(), revistaEntrada.getNome());
        Assert.assertEquals(saida.get(0).getCriador().getId(), revistaSaida.getCriador().getId());
        Assert.assertEquals(saida.get(0).getCriador().getNomeCriador(), revistaSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.get(0).getCriador().getIdadeCriador(), revistaSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.get(0).getCriador().getQtdConteudo(), revistaSaida.getCriador().getQtdConteudo());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getId(), revistaSaida.getListaHeroi().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getNome(), revistaSaida.getListaHeroi().get(0).getNome());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getListaPoder().get(0).getId(), revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getListaPoder().get(0).getDescricao(), revistaSaida.getListaHeroi().get(0).getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getCriador().getId(), revistaSaida.getListaHeroi().get(0).getCriador().getId());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getCriador().getNomeCriador(), revistaSaida.getListaHeroi().get(0).getCriador().getNomeCriador());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getCriador().getIdadeCriador(), revistaSaida.getListaHeroi().get(0).getCriador().getIdadeCriador());
        Assert.assertEquals(saida.get(0).getListaHeroi().get(0).getCriador().getQtdConteudo(), revistaSaida.getListaHeroi().get(0).getCriador().getQtdConteudo());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
