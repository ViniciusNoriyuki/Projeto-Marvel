package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class HeroiEntradaTemplate implements TemplateLoader {

    public static final String HEROI_TEMPLATE_VALIDO = "heroi template valido";
    public static final String HEROI_TEMPLATE_INVALIDO = "heroi template invalido";

    @Override
    public void load() {
        Fixture.of(HeroiEntrada.class).addTemplate(HEROI_TEMPLATE_VALIDO, new Rule(){{
            List<Long> idPoder = new ArrayList<Long>(){{add(1L);}};
            add("nome", "Homem de Ferro");
            add("listaPoder", idPoder);
            add("criador", 1L);
        }}).addTemplate(HEROI_TEMPLATE_INVALIDO, new Rule(){{
            List<Long> idPoder = new ArrayList<Long>(){{
                add(1L);
                add(2L);
                add(3L);
                add(4L);
                add(5L);
                add(6L);
            }};
            add("nome", "Homem de Ferro");
            add("listaPoder", idPoder);
            add("criador", 1L);
        }});
    }
}
