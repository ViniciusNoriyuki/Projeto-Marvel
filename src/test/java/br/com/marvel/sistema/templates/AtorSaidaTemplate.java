package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AtorSaidaTemplate implements TemplateLoader {

    public static final String ATOR_TEMPLATE_VALIDO = "ator template valido";

    @Override
    public void load() {
        Fixture.of(AtorSaida.class).addTemplate(ATOR_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nomeAtor", "Vinicius");
            add("idadeAtor", 25);
            add("heroi", one(HeroiSaida.class, HeroiSaidaTemplate.HEROI_TEMPLATE_VALIDO));
        }});
    }
}
