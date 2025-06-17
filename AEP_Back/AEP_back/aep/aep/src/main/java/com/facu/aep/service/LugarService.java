package com.facu.aep.service;

import com.facu.aep.entity.Lugar;
import com.facu.aep.repository.LugarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LugarService {
    private final LugarRepository repository;

    public LugarService(LugarRepository repository) {
        this.repository = repository;
    }

    public List<Lugar> listarTodos() {
        return repository.findAll();
    }

    public Optional<Lugar> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Lugar salvar(Lugar lugar) {
        return repository.save(lugar);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
