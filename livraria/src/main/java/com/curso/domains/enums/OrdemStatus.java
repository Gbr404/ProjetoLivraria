package com.curso.domains.enums;

public enum OrdemStatus {
    ABERTO(0, "ROLE_ABERTO"), ANDAMENTO(1, "ROLE_ANDAMENTO"), CANCELADA(2, "ROLE_CANCELADA"), CONCLUIDA(3, "ROLE_CONCLUIDA");

    private Integer id;
    private String orderstatus;

    private OrdemStatus(Integer id, String ordermstatus) {
        this.id = id;
        this.orderstatus = ordermstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdemstatus() {
        return orderstatus;
    }

    public void setOrdemstatus(String ordemstatus) {
        this.orderstatus = ordemstatus;
    }

    public static OrdemStatus toEnum(Integer id) {
        if (id == null)
            return null;
        for (OrdemStatus x : OrdemStatus.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Ordem Status Inválido");
    }

}
