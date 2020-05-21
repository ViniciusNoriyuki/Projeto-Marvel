package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class FilmeEntradaTemplate implements TemplateLoader {

    public static final String FILME_TEMPLATE_VALIDO = "filme template valido";
    public static final String FILME_TEMPLATE_INVALIDO = "filme template invalido";

    @Override
    public void load() {
        Fixture.of(FilmeEntrada.class).addTemplate(FILME_TEMPLATE_VALIDO, new Rule() {{
            List<Long> listaAtores = new ArrayList<Long>(){{add(1L);}};
            add("nomeFilme", "Filme Teste");
            add("dataLanc", "2020");
            add("atores", listaAtores);
            add("criador", 1L);
        }}).addTemplate(FILME_TEMPLATE_INVALIDO, new Rule(){{
            List<Long> listaAtores = new ArrayList<Long>(){{
                add(1L); add(2L); add(3L); add(4L); add(5L); add(6L);}};
            add("nomeFilme", "Filme Teste");
            add("dataLanc", "2020");
            add("atores", listaAtores);
            add("criador", 1L);
        }});
    }
}