package model;

import interfaces.Calculavel;

public class JogoAluguel extends Jogo implements Calculavel {

    private int dias, totalAlugados;
    private double valorDia;
    private boolean alugado;
    
    public JogoAluguel(int codigo, String nome, Categoria categoria, int dias, double valorDia) {
        super(nome, categoria, codigo);
        this.dias = dias;
        this.valorDia = valorDia;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    @Override
    public double calcularPreco() {

        return dias * valorDia;
    }

    @Override
    public String toString() {

        return super.toString() +
                " | Aluguel: R$ " +
                calcularPreco();
    }

    public int getDias() {
        return dias;
    }

    public int getTotalAlugados() {
        return totalAlugados;
    }
}