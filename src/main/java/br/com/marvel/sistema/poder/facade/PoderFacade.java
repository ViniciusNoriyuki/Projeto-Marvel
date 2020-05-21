package br.com.marvel.sistema.poder.facade;

import br.com.marvel.sistema.heroi.mapper.HeroiMapper;
import br.com.marvel.sistema.heroi.model.HeroiEntity;
import br.com.marvel.sistema.heroi.model.HeroiSaida;
import br.com.marvel.sistema.poder.mapper.PoderMapper;
import br.com.marvel.sistema.poder.model.PoderEntity;
import br.com.marvel.sistema.poder.model.PoderEntrada;
import br.com.marvel.sistema.poder.model.PoderSaida;
import br.com.marvel.sistema.poder.repository.PoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PoderFacade {

    @Autowired
    PoderRepository poderRepository;

    public PoderSaida buscarUsuario(PoderEntrada entrada) {

        // ENTRADA PARA ENTIDADE =>
        PoderEntity entidade = PoderMapper.Instance.converterEntradaParaEntity(entrada);

        // ENTIDADE PARA BANCO =>
        entidade = poderRepository.save(entidade);

        // ENTIDADE PARA SAIDA
        PoderSaida saida = PoderMapper.Instance.converterSaidaParaEntity(entidade);

        return saida;
    }

    public PoderEntity retornarPoderEntity(Long idPoder) throws Exception {
        PoderEntity entidade;
        Optional<PoderEntity> retornoBanco = poderRepository.findById(idPoder);

        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado!");
        }
        entidade = retornoBanco.get();

        return entidade;
    }

    public List<PoderSaida> listarPoderes(){
        List<PoderEntity> lista = poderRepository.findAll();

        List<PoderSaida> listaSaida = new ArrayList<>();
        for (PoderEntity saida : lista){
            listaSaida.add (PoderMapper.Instance.converterSaidaParaEntity(saida));
        }

        return listaSaida;
    }
}
