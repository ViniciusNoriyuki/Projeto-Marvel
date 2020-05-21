package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FilmeSaidaTemplate implements TemplateLoader {

    public static final String FILME_TEMPLATE_VALIDO = "FILME template valido";

    @Override
    public void load() {
        Fixture.of(FilmeSaida.class).addTemplate(FILME_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("nomeFilme", "Filme Teste");
            add("dataLanc", "2020");
            add("atores", has(1).of(AtorSaida.class, AtorSaidaTemplate.ATOR_TEMPLATE_VALIDO));
            add("criador", one(CriadorSaida.class, CriadorSaidaTemplate.CRIADOR_TEMPLATE_VALIDO));
        }});
    }
}