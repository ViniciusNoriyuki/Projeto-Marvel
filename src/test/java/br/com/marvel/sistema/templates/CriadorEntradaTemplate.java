package br.com.marvel.sistema.templates;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CriadorEntradaTemplate implements TemplateLoader {
    public static final String CRIADOR_TEMPLATE_VALIDO = "criador template valido";
    public static final String CRIADOR_TEMPLATE_INVALIDO = "criador template invalido";
    @Override
    public void load() {
        Fixture.of(CriadorEntrada.class).addTemplate(CRIADOR_TEMPLATE_VALIDO, new Rule() {{
            add("nomeCriador","Sallas");
            add("idadeCriador",20);
        }}).addTemplate(CRIADOR_TEMPLATE_INVALIDO, new Rule(){{
            add("nomeCriador",48545645);
            add("idadeCriador","454");
        }});
    }
}