package model;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private int id;
    private Usuario cliente;
    private List<JogoVenda> jogosComprados;
    private double valorTotal;
    private LocalDateTime dataVenda;


    public Venda(int id, Usuario cliente, List<JogoVenda> jogosComprados) {
        this.id = id;
        this.cliente = cliente;
        this.jogosComprados = jogosComprados;
        this.dataVenda = LocalDateTime.now();
        this.valorTotal = calcularValorTotal();
    }
    private double calcularValorTotal() {
        double total = 0;
        if (jogosComprados != null) {
            for (JogoVenda jogo : jogosComprados) {
            }
        }
        return total;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public List<JogoVenda> getJogosComprados() {
        return jogosComprados;
    }

    public void setJogosComprados(List<JogoVenda> jogosComprados) {
        this.jogosComprados = jogosComprados;
        this.valorTotal = calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }


    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}
