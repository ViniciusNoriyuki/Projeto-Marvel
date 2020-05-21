package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CriadorSaidaTemplate implements TemplateLoader {

    public static final String CRIADOR_TEMPLATE_VALIDO = "criador template valido";

    @Override
    public void load() {
        Fixture.of(CriadorSaida.class).addTemplate(CRIADOR_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nomeCriador","Sallas");
            add("idadeCriador",20);
            add("qtdConteudo", 1);
        }});
    }
}
