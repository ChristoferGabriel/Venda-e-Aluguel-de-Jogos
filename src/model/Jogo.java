package model;

import java.io.Serializable;

public abstract class Jogo implements Serializable {

    private int codigo;
    protected String nome;
    protected Categoria categoria;
    

    public Jogo(String nome, Categoria categoria, int codigo) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Categoria: " + categoria;
    }
}
