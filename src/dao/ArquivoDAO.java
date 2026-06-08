package dao;

import model.Jogo;

import java.io.*;
import java.util.ArrayList;

public class ArquivoDAO {

    private final String arquivo = "jogos.dat";

    public void salvar(ArrayList<Jogo> lista) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(arquivo));

            out.writeObject(lista);

            out.close();

            System.out.println("Dados salvos.");

        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar: "
                            + e.getMessage());
        }
    }

    public ArrayList<Jogo> carregar() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(arquivo));

            ArrayList<Jogo> lista =
                    (ArrayList<Jogo>) in.readObject();

            in.close();

            return lista;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}