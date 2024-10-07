package SisMed.service;

import SisMed.model.Usuario;
import SisMed.repository.UsuarioRepositoryInterface;

import java.util.List;

public class UsuarioService {
    private UsuarioRepositoryInterface usuarioRepository;

    public UsuarioService(UsuarioRepositoryInterface usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    public boolean loginUsuario(String userName, String senha) {
        return usuarioRepository.loginUsuario(userName, senha);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarTodos();
    }

    public Usuario filtrarUsuarioPorUserName(String userName){
        return usuarioRepository.filtrarUsuarioPorUserName(userName);
    }
}
