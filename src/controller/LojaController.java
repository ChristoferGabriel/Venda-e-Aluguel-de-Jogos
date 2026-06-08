package controller;

import dao.ArquivoDAO;
import java.util.ArrayList;
import model.*;

public class LojaController {

    private ArrayList<Jogo> jogos;

    private ArquivoDAO dao =
            new ArquivoDAO();

    public LojaController() {

        jogos = dao.carregar();
    }

    public void cadastrarJogo(Jogo jogo) {

    try {

        if (jogo == null) {
            throw new Exception("Usuário inválido.");
        }

        if (buscarJogoPorCodigo(jogo.getCodigo()) != null) {

            throw new Exception(
                "Já existe jogo com esse codigo."
            );
        }

        jogos.add(jogo);

        System.out.println("Jogo cadastrado com sucesso.");

        } catch (Exception e) {

            System.out.println("Erro no cadastro: "+ e.getMessage());
        }
    }

    public Jogo buscarJogoPorCodigo(int codigo) {

        for (Jogo j : jogos) {
            if (j.getCodigo() == codigo) {
                return j;
            }
        }

        return null;
    }

    public void adicionar(Jogo jogo) {

        jogos.add(jogo);

        dao.salvar(jogos);
    }

    public void listar() {

        for (Jogo jogo : jogos) {

            System.out.println(jogo);
        }
    }
}