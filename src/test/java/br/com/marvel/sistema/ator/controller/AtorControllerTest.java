package br.com.marvel.sistema.ator.controller;

import br.com.marvel.sistema.ator.facade.AtorFacade;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.templates.AtorEntradaTemplate;
import br.com.marvel.sistema.templates.AtorSaidaTemplate;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AtorControllerTest {

    @InjectMocks
    private AtorController atorController;

    @Mock
    private AtorFacade atorFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(atorController).build();
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }

    @Test
    public void deveCriarAtor() throws Exception {
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);
        AtorSaida atorSaida = Fixture.from(AtorSaida.class).gimme(AtorSaidaTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorFacade.criarAtor(Mockito.any(AtorEntrada.class))).thenReturn(atorSaida);

        MvcResult result = mockMvc.perform(post("/marvel/ator")
                    .content(asJsonString(atorEntrada))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

        String json = result.getResponse().getContentAsString();
        AtorSaida saida = new ObjectMapper().readValue(json, AtorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), atorSaida.getId());
        Assert.assertEquals(saida.getNomeAtor(), atorSaida.getNomeAtor());
        Assert.assertEquals(saida.getIdadeAtor(), atorSaida.getIdadeAtor());
        Assert.assertEquals(saida.getHeroi().getId(), atorSaida.getHeroi().getId());
        Assert.assertEquals(saida.getHeroi().getNome(), atorSaida.getHeroi().getNome());
        Assert.assertEquals(saida.getHeroi().getListaPoder().get(0).getId(), atorSaida.getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getHeroi().getListaPoder().get(0).getDescricao(), atorSaida.getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.getHeroi().getCriador().getId(), atorSaida.getHeroi().getCriador().getId());
        Assert.assertEquals(saida.getHeroi().getCriador().getNomeCriador(), atorSaida.getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.getHeroi().getCriador().getIdadeCriador(), atorSaida.getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getHeroi().getCriador().getQtdConteudo(), atorSaida.getHeroi().getCriador().getQtdConteudo());
    }

    @Test
    public void deveListarAtores() throws Exception {
        AtorSaida atorSaida = Fixture.from(AtorSaida.class).gimme(AtorSaidaTemplate.ATOR_TEMPLATE_VALIDO);
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);
        Long id = 1L;
        List<AtorSaida> listaAtorSaida = new ArrayList<>();
        listaAtorSaida.add(atorSaida);
        Mockito.when(atorFacade.listarAtores()).thenReturn(listaAtorSaida);

        MvcResult result = mockMvc.perform(get("/marvel/ator/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<AtorSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, AtorSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), atorSaida.getId());
        Assert.assertEquals(saida.get(0).getNomeAtor(), atorSaida.getNomeAtor());
        Assert.assertEquals(saida.get(0).getIdadeAtor(), atorSaida.getIdadeAtor());
        Assert.assertEquals(saida.get(0).getHeroi().getId(), atorSaida.getHeroi().getId());
        Assert.assertEquals(saida.get(0).getHeroi().getNome(), atorSaida.getHeroi().getNome());
        Assert.assertEquals(saida.get(0).getHeroi().getListaPoder().get(0).getId(), atorSaida.getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.get(0).getHeroi().getListaPoder().get(0).getDescricao(), atorSaida.getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.get(0).getHeroi().getCriador().getId(), atorSaida.getHeroi().getCriador().getId());
        Assert.assertEquals(saida.get(0).getHeroi().getCriador().getNomeCriador(), atorSaida.getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.get(0).getHeroi().getCriador().getIdadeCriador(), atorSaida.getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.get(0).getHeroi().getCriador().getQtdConteudo(), atorSaida.getHeroi().getCriador().getQtdConteudo());
    }

    @Test
    public void deveAlterarIdade() throws Exception {
        AtorSaida atorSaida = Fixture.from(AtorSaida.class).gimme(AtorSaidaTemplate.ATOR_TEMPLATE_VALIDO);
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_TEMPLATE_VALIDO);
        Long id = 1L;
        Mockito.when(atorFacade.alterarIdade(Mockito.anyLong(), Mockito.any(AtorEntrada.class))).thenReturn(atorSaida);

        MvcResult result = mockMvc.perform(patch("/marvel/ator/"+id+"/alterarIdade")
                .content(asJsonString(atorEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        AtorSaida saida = new ObjectMapper().readValue(json, AtorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), atorSaida.getId());
        Assert.assertEquals(saida.getNomeAtor(), atorSaida.getNomeAtor());
        Assert.assertEquals(saida.getIdadeAtor(), atorSaida.getIdadeAtor());
        Assert.assertEquals(saida.getHeroi().getId(), atorSaida.getHeroi().getId());
        Assert.assertEquals(saida.getHeroi().getNome(), atorSaida.getHeroi().getNome());
        Assert.assertEquals(saida.getHeroi().getListaPoder().get(0).getId(), atorSaida.getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getHeroi().getListaPoder().get(0).getDescricao(), atorSaida.getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.getHeroi().getCriador().getId(), atorSaida.getHeroi().getCriador().getId());
        Assert.assertEquals(saida.getHeroi().getCriador().getNomeCriador(), atorSaida.getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.getHeroi().getCriador().getIdadeCriador(), atorSaida.getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getHeroi().getCriador().getQtdConteudo(), atorSaida.getHeroi().getCriador().getQtdConteudo());

    }

    @Test
    public void deveDeletarAtor() throws Exception {
        AtorSaida atorSaida = Fixture.from(AtorSaida.class).gimme(AtorSaidaTemplate.ATOR_TEMPLATE_VALIDO);
        Long id = 1L;
        Mockito.when(atorFacade.excluirAtor(Mockito.anyLong())).thenReturn(atorSaida);

        MvcResult result = mockMvc.perform(delete("/marvel/ator/"+id+"/deletarAtor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        AtorSaida saida = new ObjectMapper().readValue(json, AtorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), atorSaida.getId());
        Assert.assertEquals(saida.getNomeAtor(), atorSaida.getNomeAtor());
        Assert.assertEquals(saida.getIdadeAtor(), atorSaida.getIdadeAtor());
        Assert.assertEquals(saida.getHeroi().getId(), atorSaida.getHeroi().getId());
        Assert.assertEquals(saida.getHeroi().getNome(), atorSaida.getHeroi().getNome());
        Assert.assertEquals(saida.getHeroi().getListaPoder().get(0).getId(), atorSaida.getHeroi().getListaPoder().get(0).getId());
        Assert.assertEquals(saida.getHeroi().getListaPoder().get(0).getDescricao(), atorSaida.getHeroi().getListaPoder().get(0).getDescricao());
        Assert.assertEquals(saida.getHeroi().getCriador().getId(), atorSaida.getHeroi().getCriador().getId());
        Assert.assertEquals(saida.getHeroi().getCriador().getNomeCriador(), atorSaida.getHeroi().getCriador().getNomeCriador());
        Assert.assertEquals(saida.getHeroi().getCriador().getIdadeCriador(), atorSaida.getHeroi().getCriador().getIdadeCriador());
        Assert.assertEquals(saida.getHeroi().getCriador().getQtdConteudo(), atorSaida.getHeroi().getCriador().getQtdConteudo());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
