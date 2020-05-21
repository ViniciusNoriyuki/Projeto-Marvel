package br.com.marvel.sistema.templates;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.poder.model.PoderSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class HeroiSaidaTemplate implements TemplateLoader {

    public static final String HEROI_TEMPLATE_VALIDO = "heroi template valido";

    @Override
    public void load() {
        Fixture.of(HeroiSaida.class).addTemplate(HEROI_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "Homem de Ferro");
            add("listaPoder", has(1).of(PoderSaida.class, PoderSaidaTemplate.PODER_TEMPLATE_VALIDO));
            add("criador", one(CriadorSaida.class, CriadorSaidaTemplate.CRIADOR_TEMPLATE_VALIDO));
        }});
    }
}
