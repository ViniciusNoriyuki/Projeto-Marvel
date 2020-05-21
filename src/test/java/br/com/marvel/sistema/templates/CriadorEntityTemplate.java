package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CriadorEntityTemplate implements TemplateLoader {

    public static final String CRIADOR_TEMPLATE_VALIDO = "criador template valido";
    public static final String CRIADOR_TEMPLATE_INVALIDO = "criador template invalido";

    @Override
    public void load() {
        Fixture.of(CriadorEntity.class).addTemplate(CRIADOR_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nomeCriador", "Vinicius");
            add("idadeCriador", 25);
            add("qtdConteudo", 1);
        }})
        .addTemplate(CRIADOR_TEMPLATE_INVALIDO, new Rule(){{
            add("id", 1L);
            add("nomeCriador", "Vinicius");
            add("idadeCriador", 25);
            add("qtdConteudo", 6);
        }});
    }
}