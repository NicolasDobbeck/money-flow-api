package br.com.fiap.money_flow_api.model;

public class Category {
    private Long id;
    private String nome;
    private String icon;
    
    public Category(Long id, String nome, String icon) {
        this.id = id;
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

}
