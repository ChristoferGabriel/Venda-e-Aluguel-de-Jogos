package controller;

import model.Venda;
import java.util.ArrayList;
import java.util.List;

public class VendaController {
    private List<Venda> historicoVendas;

    public VendaController() {
        this.historicoVendas = new ArrayList<>();
    }
    public void registrarVenda(Venda venda) {
        if (venda != null) {
            historicoVendas.add(venda);
            System.out.println("Venda registrada com sucesso para o cliente: " + venda.getCliente().getNome());
        } else {
            System.out.println("Erro: Venda inválida.");
        }
    }
    public List<Venda> listarTodasVendas() {
        return historicoVendas;
    }
    public Venda buscarVendaPorId(int id) {
        for (Venda v : historicoVendas) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }
}