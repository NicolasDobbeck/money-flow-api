package br.com.fiap.money_flow_api.model;

import java.util.Random;

public class Category {
    private Long id;
    private String nome;
    private String icon;
    
    public Category(Long id, String nome, String icon) {
        this.id = (id == null) ? new Random().nextLong() : id;
        this.nome = nome;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", nome=" + nome + ", icon=" + icon + "]";
    }
}
