package br.com.marvel.sistema.criador.controller;

import br.com.marvel.sistema.criador.facade.CriadorFacade;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.templates.CriadorEntradaTemplate;
import br.com.marvel.sistema.templates.CriadorSaidaTemplate;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CriadorControllerTest {

    @InjectMocks
    private CriadorController criadorController;

    @Mock
    private CriadorFacade criadorFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(criadorController).build();
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarCriador() throws Exception {
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);
        CriadorSaida criadorSaida = Fixture.from(CriadorSaida.class).gimme(CriadorSaidaTemplate.CRIADOR_TEMPLATE_VALIDO);
        Mockito.when(criadorFacade.buscarUsuario(Mockito.any(CriadorEntrada.class))).thenReturn(criadorSaida);

        MvcResult result = mockMvc.perform(post("/marvel/criador")
                    .content(asJsonString(criadorEntrada))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

        String json = result.getResponse().getContentAsString();
        CriadorSaida saida = new ObjectMapper().readValue(json, CriadorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), criadorSaida.getId());
        Assert.assertEquals(saida.getNomeCriador(), criadorSaida.getNomeCriador());
        Assert.assertEquals(saida.getIdadeCriador(), criadorSaida.getIdadeCriador());
        Assert.assertEquals(saida.getQtdConteudo(), criadorSaida.getQtdConteudo());
    }

    @Test
    public void deveListarUsuarios() throws Exception {
        CriadorSaida criadorSaida = Fixture.from(CriadorSaida.class).gimme(CriadorSaidaTemplate.CRIADOR_TEMPLATE_VALIDO);
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);
        Long id = 1L;
        List<CriadorSaida> listaCriadorSaida = new ArrayList<>();
        listaCriadorSaida.add(criadorSaida);
        Mockito.when(criadorFacade.listarCriadores()).thenReturn(listaCriadorSaida);

        MvcResult result = mockMvc.perform(get("/marvel/criador/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<CriadorSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, CriadorSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), criadorSaida.getId());
        Assert.assertEquals(saida.get(0).getNomeCriador(), criadorSaida.getNomeCriador());
        Assert.assertEquals(saida.get(0).getIdadeCriador(), criadorSaida.getIdadeCriador());
        Assert.assertEquals(saida.get(0).getQtdConteudo(), criadorSaida.getQtdConteudo());
    }

    @Test
    public void deveAlterarIdade() throws Exception {
        CriadorSaida criadorSaida = Fixture.from(CriadorSaida.class).gimme(CriadorSaidaTemplate.CRIADOR_TEMPLATE_VALIDO);
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_TEMPLATE_VALIDO);
        Long id = 1L;
        Mockito.when(criadorFacade.alterarIdade(Mockito.anyLong(), Mockito.any(CriadorEntrada.class))).thenReturn(criadorSaida);

        MvcResult result = mockMvc.perform(patch("/marvel/criador/"+id+"/alterarIdadeCriador")
                .content(asJsonString(criadorEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        CriadorSaida saida = new ObjectMapper().readValue(json, CriadorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), criadorSaida.getId());
        Assert.assertEquals(saida.getNomeCriador(), criadorSaida.getNomeCriador());
        Assert.assertEquals(saida.getIdadeCriador(), criadorSaida.getIdadeCriador());
        Assert.assertEquals(saida.getQtdConteudo(), criadorSaida.getQtdConteudo());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
