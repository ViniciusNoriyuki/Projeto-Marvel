package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.revista.model.RevistaEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class RevistaEntityTemplate implements TemplateLoader {

    public static final String REVISTA_TEMPLATE_VALIDO = "revista template valido";

    @Override
    public void load() {
        Fixture.of(RevistaEntity.class).addTemplate(REVISTA_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "Revista do Homem de Ferro");
            add("listaHeroi", has(1).of(HeroiEntity.class, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO));
            add("criador", one(CriadorEntity.class, CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO));
        }});
    }
}
