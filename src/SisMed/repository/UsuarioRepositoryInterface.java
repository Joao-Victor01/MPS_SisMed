package SisMed.repository;

import SisMed.model.Usuario;

import java.util.List;

public interface UsuarioRepositoryInterface {
    void salvar(Usuario usuario);

    void atualizar(Usuario usuario);

    boolean loginUsuario(String userName, String senha);
    List<Usuario> listarTodos();
    List<Usuario> listarPacientes();
    Usuario filtrarUsuarioPorUserName(String userName);
}