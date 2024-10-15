package SisMed.service;

import SisMed.model.Usuario;
import SisMed.repository.UsuarioRepositoryInterface;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepositoryInterface usuarioRepository;

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
        List<Usuario> usuarios = usuarioRepository.listarTodos();

        for (Usuario usuario : usuarios) {
            System.out.println("Nome de usuário: " + usuario.getUserName());
            System.out.println("Tipo de usuário: " + usuario.getTipoUsuario());
            System.out.println("Data de nascimento: " + usuario.getDataNascimento());
            System.out.println("-----------------------------------");
        }

        return usuarios;
    }


    public Usuario filtrarUsuarioPorUserName(String userName){
        return usuarioRepository.filtrarUsuarioPorUserName(userName);
    }
}
