package com.facu.aep.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @ManyToMany
    private List<Lugar> historicoDeLugares = new ArrayList<>();

    @ManyToMany
    private List<Lugar> lugaresFavoritos = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Lugar> getHistoricoDeLugares() {
        return historicoDeLugares;
    }

    public void setHistoricoDeLugares(List<Lugar> historicoDeLugares) {
        this.historicoDeLugares = historicoDeLugares;
    }

    public List<Lugar> getLugaresFavoritos() {
        return lugaresFavoritos;
    }

    public void setLugaresFavoritos(List<Lugar> lugaresFavoritos) {
        this.lugaresFavoritos = lugaresFavoritos;
    }
}
