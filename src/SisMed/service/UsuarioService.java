package SisMed.service;

import SisMed.memento.UsuarioMemento;
import SisMed.model.Usuario;
import SisMed.repository.UsuarioRepositoryInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.Stack;

public class UsuarioService {
    private final UsuarioRepositoryInterface usuarioRepository;
    private Stack<UsuarioMemento> mementoStack = new Stack<>();


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
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Tipo de usuário: " + usuario.getTipoUsuario());
            System.out.println("Data de nascimento: " + usuario.getDataNascimento());
            System.out.println("-----------------------------------");
        }

        return usuarios;
    }

    public Usuario filtrarUsuarioPorUserName(String userName){
        return usuarioRepository.filtrarUsuarioPorUserName(userName);
    }

    public void atualizarDadosUsuario(String userName, LocalDate novaDataNasc,
                                      String novoNome, String novoEndereco, String novaSenha) {
        Usuario usuario = filtrarUsuarioPorUserName(userName);

        if (usuario != null) {
            mementoStack.push(usuario.saveToMemento());

            if (novaDataNasc != null) {
                usuario.setDataNascimento(novaDataNasc);
            }
            if (novoNome != null && !novoNome.isEmpty()) {
                usuario.setNome(novoNome);
            }
            if (novoEndereco != null && !novoEndereco.isEmpty()) {
                usuario.setEndereco(novoEndereco);
            }
            if (novaSenha != null && !novaSenha.isEmpty()) {
                usuario.setSenha(novaSenha);
            }

            usuarioRepository.atualizar(usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void desfazerUltimaAtualizacao(Usuario usuario) {
        if (!mementoStack.isEmpty()) {
            usuario.restoreFromMemento(mementoStack.pop());
            usuarioRepository.atualizar(usuario);
            System.out.println("Última atualização desfeita com sucesso.");
        } else {
            System.out.println("Nenhuma atualização a ser desfeita.");
        }
    }
}