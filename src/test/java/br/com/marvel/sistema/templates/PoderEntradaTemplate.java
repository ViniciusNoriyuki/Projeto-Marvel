package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoderEntradaTemplate implements TemplateLoader {

    public static final String PODER_TEMPLATE_VALIDO = "poder template valido";

    @Override
    public void load() {
        Fixture.of(PoderEntrada.class).addTemplate(PODER_TEMPLATE_VALIDO, new Rule(){{
            add("descricao", "super forca");
        }});
    }
}
