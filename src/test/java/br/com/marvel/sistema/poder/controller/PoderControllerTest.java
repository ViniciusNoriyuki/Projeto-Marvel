package br.com.marvel.sistema.poder.controller;

import br.com.marvel.sistema.poder.facade.PoderFacade;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.model.PoderSaida;
import br.com.marvel.sistema.templates.PoderEntradaTemplate;
import br.com.marvel.sistema.templates.PoderSaidaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
public class PoderControllerTest {
    @InjectMocks
    private PoderController poderController;
    @Mock
    private PoderFacade poderFacade;
    private MockMvc mockMvc;
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(poderController).build();
        FixtureFactoryLoader.loadTemplates("br.com.marvel.sistema.templates");
    }
    @Test
    public void deveSalvar()throws Exception{
        //MONTAR TEMPLATE
        PoderSaida poderSaida = Fixture.from(PoderSaida.class).gimme(PoderSaidaTemplate.PODER_TEMPLATE_VALIDO);
        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_TEMPLATE_VALIDO);
        //MOCKANDO FACADE
        Mockito.when(poderFacade.buscarUsuario(Mockito.any(PoderEntrada.class))).thenReturn(poderSaida);
        //FAZER SOLICITACAO E PASSAR O RESULTADO PARA MVCRESULT
        MvcResult result = mockMvc.perform(post("/marvel/poder")
                .content(asJsonString(poderEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //TRANSFORMAR RESULTADO DO REQUEST EM STRING
        String json = result.getResponse().getContentAsString();
        // TRANSOFORMAR STRING EM OBJETO
        PoderSaida saida = new ObjectMapper().readValue(json, PoderSaida.class);
        //VALIDACOES
        Assert.notNull(saida);
//        assertThat(saida.getDescricao()).isEqualTo(poderEntrada.getDescricao());
        org.junit.Assert.assertEquals(saida.getDescricao(),poderEntrada.getDescricao());
        org.junit.Assert.assertEquals(saida.getId(),poderSaida.getId());
        org.junit.Assert.assertEquals(saida.getDescricao(),poderSaida.getDescricao());
    }
    @Test
    public void deveBuscarUsuario() throws Exception {
        //MONTANDO TEMPLATE
        PoderSaida poderSaida = Fixture.from(PoderSaida.class).gimme(PoderSaidaTemplate.PODER_TEMPLATE_VALIDO);
        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_TEMPLATE_VALIDO);
        List<PoderSaida> listaPoderesSaida = new ArrayList<>();
        listaPoderesSaida.add(poderSaida);
        //MOCKANDO FACADE
        Mockito.when(poderFacade.listarPoderes()).thenReturn(listaPoderesSaida);
        //FAZER SOLICITACAO E PASSAR O RESULTADO PARA MVCRESULT
        MvcResult result = mockMvc.perform(get("/marvel/poder")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //TRANSFORMAR RESULTADO DO REQUEST EM STRING
        String json = result.getResponse().getContentAsString();
        // TRANSOFORMAR STRING EM OBJETO
        List<PoderSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, PoderSaida[].class));
        //VALIDACOES
        Assert.notNull(saida);
        org.junit.Assert.assertEquals(listaPoderesSaida.get(0).getDescricao(),poderEntrada.getDescricao());
        org.junit.Assert.assertEquals(listaPoderesSaida.get(0).getId(),saida.get(0).getId());
        org.junit.Assert.assertEquals(listaPoderesSaida.get(0).getDescricao(),saida.get(0).getDescricao());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}