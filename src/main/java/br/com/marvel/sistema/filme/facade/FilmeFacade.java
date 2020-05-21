package br.com.marvel.sistema.filme.facade;

import br.com.marvel.sistema.ator.model.AtorEntity;
import br.com.marvel.sistema.ator.model.AtorEntrada;
import br.com.marvel.sistema.ator.repository.AtorRepository;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import br.com.marvel.sistema.filme.mapper.FilmeMapper;
import br.com.marvel.sistema.filme.model.FilmeEntity;
import br.com.marvel.sistema.filme.model.FilmeEntrada;
import br.com.marvel.sistema.filme.model.FilmeSaida;
import br.com.marvel.sistema.filme.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.JSType.toLong;

@Service
public class FilmeFacade {

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    AtorRepository atorRepository;

    @Autowired
    CriadorRepository criadorRepository;


    public FilmeSaida criarFilme(FilmeEntrada entrada) throws Exception {

        List<AtorEntity> listaAtores = criarListaDeAtores(entrada);

        CriadorEntity criador = atribuirCriador(entrada);

        // ENTRADA PARA ENTIDADE =>
        FilmeEntity entidade = FilmeMapper.Instance.converterEntradaParaEntity(entrada,listaAtores,criador);


        // ENTIDADE PARA BANCO =>
        entidade = filmeRepository.save(entidade);

        // ENTIDADE PARA SAIDA
       FilmeSaida saida = FilmeMapper.Instance.converterSaidaParaEntity(entidade);

        return saida;
    }

    public CriadorEntity atribuirCriador(FilmeEntrada entrada) throws Exception {

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

    public List<AtorEntity> criarListaDeAtores(FilmeEntrada entrada) throws Exception {

        if(entrada.getAtores().size()<=5){
            List<AtorEntity> listaAtores = new ArrayList();

            for (int i=0;i<entrada.getAtores().size();i++){
                Optional<AtorEntity> optionalAtorEntity = atorRepository.findById(entrada.getAtores().get(i));
                if(!optionalAtorEntity.isPresent()){
                    throw new Exception("Lista de atores Vazia");
                }
                listaAtores.add(optionalAtorEntity.get());
            }
            return listaAtores;
        }else {
            throw new Exception("Filme possui mais que 5 atores");
        }
    }

    public FilmeSaida alterarDataLancamento(Long idFilme , FilmeEntrada entrada) throws Exception {

        FilmeEntity entidade;
        Optional<FilmeEntity> retornoBanco = filmeRepository.findById(idFilme);
        if (!retornoBanco.isPresent()){
            throw new Exception("Nao encontrado");
        }

        entidade = retornoBanco.get();

        entidade.setDataLanc(entrada.getDataLanc());

        entidade = filmeRepository.save(entidade);

        return  FilmeMapper.Instance.converterSaidaParaEntity(entidade);
    }
    
    public List<FilmeSaida> listarFilmes() {

        List<FilmeEntity> lista = filmeRepository.findAll();

        List<FilmeSaida> listaSaida = new ArrayList<>();
        for (FilmeEntity saida : lista) {
            listaSaida.add (FilmeMapper.Instance.converterSaidaParaEntity(saida));
        }

        return listaSaida;
    }

}
