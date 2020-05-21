package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.poder.model.PoderSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
public class PoderSaidaTemplate implements TemplateLoader {
    public static final String PODER_TEMPLATE_VALIDO = "poder template valido";
    @Override
    public void load() {
        Fixture.of(PoderSaida.class).addTemplate(PODER_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("descricao", "super forca");
        }});
    }
}