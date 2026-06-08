package model;

import interfaces.Calculavel;

public class JogoVenda extends Jogo implements Calculavel {

    private double preco;

    public JogoVenda(int codigo, String nome, Categoria categoria, double preco) {
        super(nome, categoria, codigo);
        this.preco = preco;
    }

    @Override
    public double calcularPreco() {
        return preco;
    }

    @Override
    public String toString() {

        return super.toString() +
                " | Venda: R$ " +
                calcularPreco();
    }
}