package SisMed.factory;

import SisMed.model.Paciente;
import SisMed.model.Usuario;

public class PacienteFactory implements UsuarioFactory {
    @Override
    public Usuario criarUsuario() {
        return new Paciente();
    }
}