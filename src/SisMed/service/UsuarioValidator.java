package SisMed.service;

import SisMed.model.Usuario;

public class UsuarioValidator {
    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (usuario.getCpf() == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        if (usuario.getUserName() == null){
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
        if (usuario.getSenha() == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo.");
        }
    }
}
