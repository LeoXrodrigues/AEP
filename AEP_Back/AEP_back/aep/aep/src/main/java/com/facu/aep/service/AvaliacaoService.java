package com.facu.aep.service;

import com.facu.aep.entity.Avaliacao;
import com.facu.aep.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {
    private final AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public List<Avaliacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<Avaliacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}