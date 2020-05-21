package br.com.marvel.sistema.heroi.controller;

import br.com.marvel.sistema.criador.controller.CriadorController;
import br.com.marvel.sistema.criador.facade.CriadorFacade;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.heroi.facade.HeroiFacade;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.templates.CriadorEntradaTemplate;
import br.com.marvel.sistema.templates.CriadorSaidaTemplate;
import br.com.marvel.sistema.templates.HeroiEntradaTemplate;
import br.com.marvel.sistema.templates.HeroiSaidaTemplate;
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
public class HeroiControllerTest {

    @InjectMocks
    private HeroiController heroiController;

    @Mock
    private HeroiFacade heroiFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(heroiController).build();
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarHeroi() throws Exception {
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);
        HeroiSaida heroiSaida = Fixture.from(HeroiSaida.class).gimme(HeroiSaidaTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(heroiFacade.criarHeroi(Mockito.any(HeroiEntrada.class))).thenReturn(heroiSaida);

        MvcResult result = mockMvc.perform(post("/marvel/heroi")
                    .content(asJsonString(heroiEntrada))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

        String json = result.getResponse().getContentAsString();
        HeroiSaida saida = new ObjectMapper().readValue(json, HeroiSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getNome(), heroiEntrada.getNome());
        Assert.assertEquals(saida.getId(), heroiSaida.getId());
        Assert.assertEquals(saida.getNome(), heroiSaida.getNome());
        Assert.assertEquals(saida.getCriador().getId(), heroiSaida.getCriador().getId());
        Assert.assertEquals(saida.getCriador().getNomeCriador(), heroiSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.getCriador().getIdadeCriador(), heroiSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getCriador().getQtdConteudo(), heroiSaida.getCriador().getQtdConteudo());
        Assert.assertEquals(saida.getListaPoder().get(0).getId(), heroiSaida.getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getListaPoder().get(0).getDescricao(), heroiSaida.getListaPoder().get(0).getDescricao());
    }

    @Test
    public void deveListarHerois() throws Exception {
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_TEMPLATE_VALIDO);
        HeroiSaida heroiSaida = Fixture.from(HeroiSaida.class).gimme(HeroiSaidaTemplate.HEROI_TEMPLATE_VALIDO);
        Long id = 1L;
        List<HeroiSaida> listaHeroiSaida = new ArrayList<>();
        listaHeroiSaida.add(heroiSaida);
        Mockito.when(heroiFacade.listarHerois()).thenReturn(listaHeroiSaida);

        MvcResult result = mockMvc.perform(get("/marvel/heroi/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<HeroiSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, HeroiSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getNome(), heroiEntrada.getNome());
        Assert.assertEquals(saida.get(0).getId(), heroiSaida.getId());
        Assert.assertEquals(saida.get(0).getNome(), heroiSaida.getNome());
        Assert.assertEquals(saida.get(0).getCriador().getId(), heroiSaida.getCriador().getId());
        Assert.assertEquals(saida.get(0).getCriador().getNomeCriador(), heroiSaida.getCriador().getNomeCriador());
        Assert.assertEquals(saida.get(0).getCriador().getIdadeCriador(), heroiSaida.getCriador().getIdadeCriador());
        Assert.assertEquals(saida.get(0).getCriador().getQtdConteudo(), heroiSaida.getCriador().getQtdConteudo());
        Assert.assertEquals(saida.get(0).getListaPoder().get(0).getId(), heroiSaida.getListaPoder().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaPoder().get(0).getDescricao(), heroiSaida.getListaPoder().get(0).getDescricao());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
