package SisMed.padroes.factory;

import SisMed.model.Medico;
import SisMed.model.Usuario;

public class MedicoFactory implements UsuarioFactory {
    @Override
    public Usuario criarUsuario() {
        return new Medico();
    }
}