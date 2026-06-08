package controller;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {
        usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(Usuario usuario) {

    try {

        if (usuario == null) {
            throw new Exception("Usuário inválido.");
        }

        if (buscarUsuarioPorId(usuario.getId()) != null) {

            throw new Exception(
                "Já existe usuário com esse ID."
            );
        }

        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso.");

        } catch (Exception e) {

            System.out.println("Erro no cadastro: "+ e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorId(int id) {

        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public void listarUsuarios() {

        if (usuarios.isEmpty()) {

            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario u : usuarios) {

            System.out.println("====================");
            System.out.println(u);
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}