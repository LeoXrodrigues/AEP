package com.facu.aep.service;

import com.facu.aep.entity.Localizacao;
import com.facu.aep.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacaoService {
    private final LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository) {
        this.repository = repository;
    }

    public List<Localizacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<Localizacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Localizacao salvar(Localizacao localizacao) {
        return repository.save(localizacao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}