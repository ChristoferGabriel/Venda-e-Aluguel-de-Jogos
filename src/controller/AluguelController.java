package controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.Aluguel;
import model.Jogo;
import model.JogoAluguel;
import model.Usuario;

public class AluguelController {
    private ArrayList<Aluguel> alugueis;

    public AluguelController() {
        alugueis = new ArrayList<>();
    }

    public void RealizarAluguel(Usuario usuario, Jogo jogo, JogoAluguel jogoAluguel) {

        try {
            if (usuario == null) {
                throw new Exception("Usuário não encontrado.");
            }

            if (jogo == null) {
                throw new Exception("Jogo não encontrado.");
            }

            if (usuario.isAluguelAtivo()) {
                throw new Exception("Usuário já possui empréstimo.");
            }

            if (jogoAluguel.isAlugado()) {
                throw new Exception("Jogo indisponível.");
            }

            usuario.setAluguelAtivo(true);
            jogoAluguel.setAlugado(true);

            Aluguel aluguel = new Aluguel(jogoAluguel, usuario);
            alugueis.add(aluguel);

            System.out.println("Aluguel realizado com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao realizar Aluguel: " + e.getMessage());
        }
    }

    public void devolverJogo(Aluguel aluguel) {

        try {
            if (aluguel == null) {
                throw new Exception("Aluguel não encontrado.");
            }

            if (aluguel.isDevolvido()) {
                throw new Exception("Jogo já devolvido.");
            }

            aluguel.devolverJogo();

            Jogo jogo = aluguel.getJogo();
            Usuario usuario = aluguel.getUsuario();

            usuario.setAluguelAtivo(false);

            long diasAtraso = ChronoUnit.DAYS.between(
                aluguel.getDataDevolucaoPrevista(),
                aluguel.getDataDevolucaoEfetiva()
            );

            String mensagemFinal = "";

            if (diasAtraso > 0) {
                mensagemFinal = "Jogo devolvido com atraso de " + diasAtraso + " dias.";
            } else {
                mensagemFinal = "Jogo devolvido no prazo.";
            }

            System.out.println(mensagemFinal);

        } catch (Exception e) {
            System.out.println("Erro na devolução: " + e.getMessage());
        }
    }

    public void ListarAlugados() {
        String textoFinal = "";

        for (Aluguel a : alugueis) {
            textoFinal += "Jogo: " + a.getJogo().getNome() + "\n";
            textoFinal += "Usuario: " + a.getUsuario().getNome() + "\n";
            textoFinal += "Previsto: " + a.getDataDevolucaoPrevista() + "\n";
            textoFinal += "=======================================\n";
        }

        System.out.print(textoFinal);
    }

    public void jogosMaisPopulares(List<JogoAluguel> jogosAlugados) {

        jogosAlugados.sort((l1, l2) ->
            Integer.compare(
                l2.getTotalAlugados(),
                l1.getTotalAlugados()
            )
        );

        String textoFinal = "";

        for (JogoAluguel j : jogosAlugados) {
            textoFinal += j.getNome() + " - Empréstimos: " + j.getTotalAlugados() + "\n";
        }

        System.out.print(textoFinal);
    }

    public void ListarAtrasos() {

        LocalDate hoje = LocalDate.now();

        alugueis.sort((a1, a2) -> {
            long atraso1 = ChronoUnit.DAYS.between(a1.getDataDevolucaoPrevista(), hoje);
            long atraso2 = ChronoUnit.DAYS.between(a2.getDataDevolucaoPrevista(), hoje);
            return Long.compare(atraso2, atraso1);
        });

        String textoFinal = "";

        for (Aluguel a : alugueis) {

            if (!a.isDevolvido() && hoje.isAfter(a.getDataDevolucaoPrevista())) {

                long atraso = ChronoUnit.DAYS.between(a.getDataDevolucaoPrevista(), hoje);

                textoFinal += a.getUsuario().getNome() + " está com " + atraso + " dias de atraso.\n";
            }
        }

        System.out.print(textoFinal);
    }

    public Aluguel buscarAluguelAtivo(Usuario usuario) {
        for (Aluguel a : alugueis) {
            if (a.getUsuario().getId() == usuario.getId() && !a.isDevolvido()) {
                return a;
            }
        }
        return null;
    }
}