package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private Long id;
    private String nome;

    public CategoriaDTO() {}

    public CategoriaDTO(Categoria categoria) {
        id = categoria.getId();
        nome = categoria.getNome();
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
}
