package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AtorEntityTemplate implements TemplateLoader {

    public static final String ATOR_TEMPLATE_VALIDO = "ator template valido";

    @Override
    public void load() {
        Fixture.of(AtorEntity.class).addTemplate(ATOR_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nomeAtor", "Vinicius");
            add("idadeAtor", 25);
            add("heroi", one(HeroiEntity.class, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO));
        }});
    }
}
