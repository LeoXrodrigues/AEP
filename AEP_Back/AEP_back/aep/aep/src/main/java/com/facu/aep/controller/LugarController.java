package com.facu.aep.controller;

import com.facu.aep.entity.Lugar;
import com.facu.aep.service.LugarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares")
public class LugarController {

    private final LugarService lugarService;

    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @GetMapping
    public ResponseEntity<List<Lugar>> listarTodos() {
        return ResponseEntity.ok(lugarService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lugar> buscarPorId(@PathVariable Long id) {
        return lugarService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lugar> salvar(@RequestBody Lugar lugar) {
        return ResponseEntity.ok(lugarService.salvar(lugar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lugarService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
