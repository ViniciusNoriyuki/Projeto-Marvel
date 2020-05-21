package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class RevistaEntradaTemplate implements TemplateLoader {

    public static final String REVISTA_TEMPLATE_VALIDO = "revista template valido";

    @Override
    public void load() {
        Fixture.of(RevistaEntrada.class).addTemplate(REVISTA_TEMPLATE_VALIDO, new Rule(){{
            List<Long> listaIdHeroi = new ArrayList<Long>(){{add(1L);}};
            add("nome", "Revista do Homem de Ferro");
            add("listaHeroi", listaIdHeroi);
            add("criador", 1L);
        }});
    }
}
