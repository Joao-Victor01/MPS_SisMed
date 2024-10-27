package SisMed.padroes.factory;

import SisMed.model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UsuarioFactoryRegistry {
    private static Map<Integer, UsuarioFactory> factoryMap = new HashMap<>();

    static {
        factoryMap.put(1, new MedicoFactory());
        factoryMap.put(2, new PacienteFactory());
        factoryMap.put(3, new AdminFactory());
    }

    public static Usuario criarUsuario(Integer tipoUsuario) {
        UsuarioFactory factory = factoryMap.get(tipoUsuario);
        if (factory == null) {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }
        return factory.criarUsuario();
    }
}
