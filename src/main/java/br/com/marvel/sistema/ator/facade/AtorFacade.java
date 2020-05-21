package br.com.marvel.sistema.ator.facade;

import br.com.marvel.sistema.ator.mapper.AtorMapper;
import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.model.AtorSaida;
import br.com.marvel.sistema.ator.repository.AtorRepository;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.repository.FilmeRepository;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiEntrada;
import br.com.marvel.sistema.heroi.repository.HeroiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtorFacade {
//teste git autor
    @Autowired
    AtorRepository atorRepository;

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    HeroiRepository heroiRepository;

    public AtorSaida criarAtor(AtorEntrada entrada) throws Exception {


        HeroiEntity entidadeHeroi;
        Optional<HeroiEntity> retornoBancoHeroi = heroiRepository.findById(entrada.getHeroi());
        if (!retornoBancoHeroi.isPresent()){
            throw new Exception("Heroi não encontrado");
        }
        entidadeHeroi = retornoBancoHeroi.get();

        // ENTRADA PARA ENTIDADE =>
        AtorEntity atorEntity = AtorMapper.Instance.converterEntradaParaEntity(entrada,entidadeHeroi);

        // ENTIDADE PARA BANCO =>
        atorEntity = atorRepository.save(atorEntity);

        // ENTIDADE PARA SAIDA
       AtorSaida saida = AtorMapper.Instance.converterEntityParaSaida(atorEntity);

        return saida;
    }

    public AtorSaida alterarIdade(Long idAtor , AtorEntrada entrada) throws Exception {

        AtorEntity entidade;
        Optional<AtorEntity> retornoBanco = atorRepository.findById(idAtor);
        if (!retornoBanco.isPresent()){
            throw new Exception("Nao encontrado");

        }

        entidade = retornoBanco.get();

        entidade.setIdadeAtor(entrada.getIdadeAtor());

        entidade = atorRepository.save(entidade);

        return  AtorMapper.Instance.converterEntityParaSaida(entidade);
    }

//    public AtorSaida adicionarFilmesAoAtor(Long id, AtorEntrada entrada) throws Exception {
//
//        AtorEntity entidade = null;
//
//        List<FilmeEntity> filmeEntityList = AtorMapper.Instance.converterListaLongParaEntity(entrada.getFilmes());
//
//        entidade.setFilmes(filmeEntityList);
//
//        Optional<AtorEntity> retornoBanco = atorRepository.findById(id);
//        if (!retornoBanco.isPresent()){
//            throw new Exception("Ator nao encontrado");
//        }
//
////        List <FilmeEntity> listaDeFilmes = new ArrayList();
////
////        for(int i=0; i<entrada.getFilmes().size();i++){
////            Optional<FilmeEntity> retornoBancoFilmes = filmeRepository.findById(entrada.getFilmes().get(i));
////            listaDeFilmes.add(retornoBancoFilmes.get());
////        }
//
//        entidade = retornoBanco.get();
//
//        entidade.setFilmes(listaDeFilmes);
//
//        entidade = atorRepository.save(entidade);
//
//        return  AtorMapper.Instance.converterEntityParaSaida(entidade);
//    }


    public List<AtorSaida> listarAtores() {

        List<AtorEntity> lista = atorRepository.findAll();

        List<AtorSaida> listaSaida = new ArrayList<>();
        for (AtorEntity saida : lista) {
            listaSaida.add (AtorMapper.Instance.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }


    public AtorSaida excluirAtor(Long idUsuario) throws Exception {
        AtorEntity entidade;
        Optional<AtorEntity> retornoBanco = atorRepository.findById(idUsuario);
        if (!retornoBanco.isPresent()){
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();
        atorRepository.delete(entidade);
        return AtorMapper.Instance.converterEntityParaSaida(entidade);
    }
}
