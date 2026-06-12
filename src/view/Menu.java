package view;

import java.util.Scanner;

import controller.AluguelController;
import controller.LojaController;
import controller.UsuarioController;
import controller.VendaController;
import model.Categoria;
import model.Jogo;
import model.JogoAluguel;
import model.JogoVenda;
import model.Usuario;

public class Menu {

    private Scanner sc;

    private LojaController lojaController;
    private UsuarioController usuarioController;
    private AluguelController aluguelController;
    private VendaController vendaController;

    public Menu() {

        sc = new Scanner(System.in);

        lojaController = new LojaController();
        usuarioController = new UsuarioController();
        aluguelController = new AluguelController();
        vendaController = new VendaController();
    }

    public void executar() {

        int opcao;

        do {

            exibirMenu();

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1:
                    cadastrarUsuario();
                    break;

                case 2:
                    cadastrarJogoAluguel();
                    break;

                case 3:
                    cadastrarJogoVenda();
                    break;

                case 4:
                    listarJogos();
                    break;

                case 5:
                    listarUsuarios();
                    break;

                case 6:
                    realizarAluguel();
                    break;

                case 7:
                    listarAlugados();
                    break;

                case 8:
                    devolverJogo();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    private void exibirMenu() {

        System.out.println("\n===== LOCADORA & LOJA =====");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Cadastrar jogo para aluguel");
        System.out.println("3 - Cadastrar jogo para venda");
        System.out.println("4 - Listar jogos");
        System.out.println("5 - Listar usuários");
        System.out.println("6 - Realizar aluguel");
        System.out.println("7 - Listar alugados");
        System.out.println("8 - Devolver jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarUsuario() {

        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Usuario usuario =
            new Usuario(id, nome, email, false);

        usuarioController.cadastrarUsuario(usuario);
    }

    private void cadastrarJogoAluguel() {

        System.out.print("Código: ");
        int codigo = Integer.parseInt(sc.nextLine());

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        Categoria categoria =
            Categoria.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Dias de aluguel: ");
        int dias = Integer.parseInt(sc.nextLine());

        System.out.print("Valor por dia: ");
        double valorDia =
            Double.parseDouble(sc.nextLine());

        JogoAluguel jogo =
            new JogoAluguel(
                codigo,
                nome,
                categoria,
                dias,
                valorDia
            );

        lojaController.cadastrarJogo(jogo);
    }

    private void cadastrarJogoVenda() {

        System.out.print("Código: ");
        int codigo = Integer.parseInt(sc.nextLine());

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        Categoria categoria =
            Categoria.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Preço de venda: ");
        double precoVenda = Double.parseDouble(sc.nextLine());

        JogoVenda jogo = 
            new JogoVenda(
                codigo, 
                nome, 
                categoria, 
                precoVenda
            );

        lojaController.cadastrarJogo(jogo); 
        System.out.println("Jogo cadastrado para venda com sucesso!");
    }

    private void listarJogos() {

        lojaController.listar();
    }

    private void listarUsuarios() {

        usuarioController.listarUsuarios();
    }

    private void realizarAluguel() {

        System.out.print("ID do usuário: ");
        int idUsuario =
            Integer.parseInt(sc.nextLine());

        System.out.print("Código do jogo: ");
        int codigoJogo =
            Integer.parseInt(sc.nextLine());

        Usuario usuario =
            usuarioController.buscarUsuarioPorId(idUsuario);

        Jogo jogo =
            lojaController.buscarJogoPorCodigo(codigoJogo);

        if (jogo instanceof JogoAluguel) {

            aluguelController.RealizarAluguel(
                usuario,
                jogo,
                (JogoAluguel) jogo
            );

        } else {

            System.out.println(
                "Esse jogo não pode ser alugado."
            );
        }
    }

    private void listarAlugados() {

        aluguelController.ListarAlugados();
    }

    private void devolverJogo() {

        System.out.print("ID do usuário: ");
        int id =
            Integer.parseInt(sc.nextLine());

        Usuario usuario =
            usuarioController.buscarUsuarioPorId(id);

        if (usuario == null) {

            System.out.println(
                "Usuário não encontrado."
            );

            return;
        }

        aluguelController.devolverJogo(
            aluguelController.buscarAluguelAtivo(usuario)
        );
    }
}