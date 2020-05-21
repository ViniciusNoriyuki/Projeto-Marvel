package br.com.marvel.sistema.heroi.facade;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.heroi.mapper.HeroiMapper;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.heroi.repository.HeroiRepository;
import br.com.marvel.sistema.poder.facade.PoderFacade;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.repository.PoderRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroiFacade {

    @Autowired
    HeroiRepository heroiRepository;
    @Autowired
    PoderFacade poderFacade;

    @Autowired
    CriadorRepository criadorRepository;

    public HeroiSaida criarHeroi(HeroiEntrada entrada) throws Exception {

        // ENTRADA PARA ENTIDADE =>
        CriadorEntity criador = atribuirCriador(entrada);
        verificarQuantidadePoderes(entrada);
        List<PoderEntity> listaPoderEntity = preencherListaPoder(entrada);
        HeroiEntity entidade = HeroiMapper.Instance.converterEntradaParaEntity(entrada, listaPoderEntity, criador);

        // ENTIDADE PARA BANCO =>
        entidade = heroiRepository.save(entidade);

        // ENTIDADE PARA SAIDA
        HeroiSaida saida = HeroiMapper.Instance.converterSaidaParaEntity(entidade);

        return saida;
    }

    public List<PoderEntity> preencherListaPoder(HeroiEntrada entrada) throws Exception {
        List<PoderEntity> listaPoder = new ArrayList<>();
        for (int i = 0; i < entrada.getListaPoder().size(); i++){
            PoderEntity poderEntity = poderFacade.retornarPoderEntity(entrada.getListaPoder().get(i));
            listaPoder.add(poderEntity);
        }
        return listaPoder;
    }

    public CriadorEntity atribuirCriador(HeroiEntrada entrada) throws Exception {

        CriadorEntity criador;
        Optional<CriadorEntity> optinalCriador = criadorRepository.findById(entrada.getCriador());
        if(!optinalCriador.isPresent()){
            throw new Exception("Criador não encontrado");
        }
        criador = optinalCriador.get();

        if(criador.getQtdConteudo()<5){
            criador.setQtdConteudo(criador.getQtdConteudo()+1);
            criadorRepository.save(criador);
        }else {
            throw new Exception("Criador ja possui 5 conteudos");
        }
        return criador;
    }


    public void verificarQuantidadePoderes(HeroiEntrada entrada) throws Exception{
        if (entrada.getListaPoder().size() > 4){
            throw new Exception("Heroi nao pode ter mais que 5 poderes");
        }
    }

    public List<HeroiSaida> listarHerois(){
        List<HeroiEntity> lista = heroiRepository.findAll();

        List<HeroiSaida> listaSaida = new ArrayList<>();
        for (HeroiEntity saida : lista){
            listaSaida.add (HeroiMapper.Instance.converterSaidaParaEntity(saida));
        }

        return listaSaida;
    }

    public HeroiEntity retornarHeroiEntity(Long idHeroi) throws Exception {
        HeroiEntity entidade;
        Optional<HeroiEntity> retornoBanco = heroiRepository.findById(idHeroi);

        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado!");
        }
        entidade = retornoBanco.get();

        return entidade;
    }
}
