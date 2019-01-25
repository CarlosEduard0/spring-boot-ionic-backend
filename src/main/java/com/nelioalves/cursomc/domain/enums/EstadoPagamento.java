package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {
    PENDENTE(1L, "Pendente"),
    QUITADO(2L, "Quitado"),
    CANCELADO(3L, "Cancelado");

    private Long id;
    private String descricao;

    EstadoPagamento(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Long id) {
        if(id == null) return null;
        for(EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if(id.equals(estadoPagamento.getId())) {
                return estadoPagamento;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
