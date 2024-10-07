package SisMed.repository;

import SisMed.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryInterface {
    void salvar(Usuario usuario);
    boolean loginUsuario(String userName, String senha);
    List<Usuario> listarTodos();
    Usuario filtrarUsuarioPorUserName(String userName);
}
