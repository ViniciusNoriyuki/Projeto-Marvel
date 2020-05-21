package br.com.marvel.sistema.revista.facade;

import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.heroi.facade.HeroiFacade;
import br.com.marvel.sistema.heroi.mapper.HeroiMapper;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.revista.mapper.RevistaMapper;
import br.com.marvel.sistema.revista.model.RevistaEntity;
import br.com.marvel.sistema.revista.model.RevistaEntrada;
import br.com.marvel.sistema.revista.model.RevistaSaida;
import br.com.marvel.sistema.revista.repository.RevistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RevistaFacade {

    @Autowired
    RevistaRepository revistaRepository;
    @Autowired
    HeroiFacade heroiFacade;
    @Autowired
    CriadorRepository criadorRepository;

    public RevistaSaida buscarUsuario(RevistaEntrada entrada) throws Exception {

        // ENTRADA PARA ENTIDADE =>
        CriadorEntity criador = atribuirCriador(entrada);
        List<HeroiEntity> listaHeroiEntity = preencherListaHeroi(entrada);
        RevistaEntity entidade = RevistaMapper.Instance.converterEntradaParaEntity(entrada, listaHeroiEntity,criador);

        // ENTIDADE PARA BANCO =>
        entidade = revistaRepository.save(entidade);

        // ENTIDADE PARA SAIDA
       RevistaSaida saida = RevistaMapper.Instance.converterSaidaParaEntity(entidade);

        return saida;
    }

    public List<HeroiEntity> preencherListaHeroi(RevistaEntrada entrada) throws Exception {
        List<HeroiEntity> listaHeroi = new ArrayList<>();

        for (int i = 0; i < entrada.getListaHeroi().size(); i++){
            HeroiEntity heroiEntity = heroiFacade.retornarHeroiEntity(entrada.getListaHeroi().get(i));
            listaHeroi.add(heroiEntity);
        }
        return listaHeroi;
    }

    public CriadorEntity atribuirCriador(RevistaEntrada entrada) throws Exception {

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

    public List<RevistaSaida> listarRevistas(){
        List<RevistaEntity> lista = revistaRepository.findAll();

        List<RevistaSaida> listaSaida = new ArrayList<>();
        for (RevistaEntity saida : lista){
            listaSaida.add (RevistaMapper.Instance.converterSaidaParaEntity(saida));
        }

        return listaSaida;
    }
}
