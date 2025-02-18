package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {
    PESSOA_FISICA(1L, "Pessoa Física"),
    PESSOA_JURIDICA(2L, "Pessoa Jurídica");

    private Long id;
    private String descricao;

    TipoCliente(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static TipoCliente toEnum(Long id) {
        if(id == null) return null;
        for(TipoCliente tipoCliente : TipoCliente.values()) {
            if(id.equals(tipoCliente.getId())) {
                return tipoCliente;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
