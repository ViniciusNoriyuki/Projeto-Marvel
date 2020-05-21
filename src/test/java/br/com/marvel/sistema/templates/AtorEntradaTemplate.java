package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AtorEntradaTemplate implements TemplateLoader {

    public static final String ATOR_TEMPLATE_VALIDO = "ator template valido";
    public static final String ATOR_TEMPLATE_INVALIDO = "ator template invalido";

    @Override
    public void load() {
        Fixture.of(AtorEntrada.class).addTemplate(ATOR_TEMPLATE_VALIDO, new Rule(){{
            add("nomeAtor", "Vinicius");
            add("idadeAtor", 25);
            add("heroi", 1L);
        }}).addTemplate(ATOR_TEMPLATE_INVALIDO, new Rule(){{
            add("nomeAtor", "Vinicius");
            add("idadeAtor", 25);
            add("heroi", null);
        }});
    }
}
