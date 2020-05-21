package br.com.marvel.sistema.criador.facade;

import br.com.marvel.sistema.criador.mapper.CriadorMapper;
import br.com.marvel.sistema.criador.model.CriadorEntity;
import br.com.marvel.sistema.criador.model.CriadorEntrada;
import br.com.marvel.sistema.criador.model.CriadorSaida;
import br.com.marvel.sistema.criador.repository.CriadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriadorFacade {

    @Autowired
    CriadorRepository criadorRepository;

    public CriadorSaida buscarUsuario(CriadorEntrada entrada) {

        // ENTRADA PARA ENTIDADE =>
        CriadorEntity entidade = CriadorMapper.Instance.converterEntradaParaEntity(entrada);
        entidade.setQtdConteudo(0);
        // ENTIDADE PARA BANCO =>
        entidade = criadorRepository.save(entidade);

        // ENTIDADE PARA SAIDA
       CriadorSaida saida = CriadorMapper.Instance.converterEntityParaSaida(entidade);

        return saida;
    }

    public CriadorSaida alterarIdade(Long idCriador , CriadorEntrada entrada) throws Exception {

        CriadorEntity entidade;
        Optional<CriadorEntity> retornoBanco = criadorRepository.findById(idCriador);
        if (!retornoBanco.isPresent()){
            throw new Exception("Nao encontrado");

        }

        entidade = retornoBanco.get();

        entidade.setIdadeCriador(entrada.getIdadeCriador());

        entidade = criadorRepository.save(entidade);

        return  CriadorMapper.Instance.converterEntityParaSaida(entidade);
    }
    
    public List<CriadorSaida> listarCriadores() {

        List<CriadorEntity> lista = criadorRepository.findAll();

        List<CriadorSaida> listaSaida = new ArrayList<>();
        for (CriadorEntity saida : lista) {
            listaSaida.add (CriadorMapper.Instance.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }
}
