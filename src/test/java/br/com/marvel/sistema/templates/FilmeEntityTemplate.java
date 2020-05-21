package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FilmeEntityTemplate implements TemplateLoader {

    public static final String FILME_TEMPLATE_VALIDO = "FILME template valido";

    @Override
    public void load() {
        Fixture.of(FilmeEntity.class).addTemplate(FILME_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("nomeFilme", "Homem Aranha o Filme");
            add("dataLanc", "2020");
            add("atores", has(1).of(AtorEntity.class, AtorEntityTemplate.ATOR_TEMPLATE_VALIDO));
            add("criador", one(CriadorEntity.class, CriadorEntityTemplate.CRIADOR_TEMPLATE_VALIDO));
        }});
    }
}