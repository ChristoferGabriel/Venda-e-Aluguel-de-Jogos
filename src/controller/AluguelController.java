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

    public AluguelController(){
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
            throw new Exception(
                "Usuário já possui empréstimo."
            );
        }

        if (jogoAluguel.isAlugado()) {
            throw new Exception(
                "Jogo indisponível."
            );
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

        long diasAtraso =
            ChronoUnit.DAYS.between(
                aluguel.getDataDevolucaoPrevista(),
                aluguel.getDataDevolucaoEfetiva()
            );

        if (diasAtraso > 0) {

            System.out.println("Jogo devolvido com atraso de " + diasAtraso + " dias.");

        } else {
            System.out.println("Jogo devolvido no prazo.");
        }

        } catch (Exception e) {
            System.out.println("Erro na devolução: " + e.getMessage());
        }
    }

    public void ListarAlugados(){
        for(Aluguel a : alugueis){
            System.out.println("Jogo: " + a.getJogo().getNome());
            System.out.println("Usuario: " + a.getUsuario().getNome());
            System.out.println("Previsto: " + a.getDataDevolucaoPrevista());
            System.out.println("=======================================");
        }
    }

    public void jogosMaisPopulares(List<JogoAluguel> jogosAlugados) {

        jogosAlugados.sort((l1, l2) ->
            Integer.compare(
                l2.getTotalAlugados(),
                l1.getTotalAlugados()
            )
        );

        for (JogoAluguel j : jogosAlugados) {
            System.out.println( j.getNome() + " - Empréstimos: " + j.getTotalAlugados());
        }
    }

    public void ListarAtrasos() {

        LocalDate hoje = LocalDate.now();

        alugueis.sort((a1, a2) -> {

            long atraso1 =
                ChronoUnit.DAYS.between(
                    a1.getDataDevolucaoPrevista(),
                    hoje
                );

            long atraso2 =
                ChronoUnit.DAYS.between(
                    a2.getDataDevolucaoPrevista(),
                    hoje
                );

            return Long.compare(atraso2, atraso1);
        });

        for (Aluguel a : alugueis) {

            if (!a.isDevolvido()
                && hoje.isAfter(a.getDataDevolucaoPrevista())) {

                long atraso =
                    ChronoUnit.DAYS.between(
                        a.getDataDevolucaoPrevista(),
                        hoje
                    );

                System.out.println(
                    a.getUsuario().getNome() + " está com " + atraso + " dias de atraso.");
            }
        }
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
