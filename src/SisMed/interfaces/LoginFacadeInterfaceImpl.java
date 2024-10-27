package SisMed.interfaces;

import SisMed.login.LoginStrategy;

import java.util.HashMap;
import java.util.Map;

public class LoginFacadeInterfaceImpl implements LoginFacadeInterface {
    private Map<Integer, LoginStrategy> loginStrategies = new HashMap<>();

    public LoginFacadeInterfaceImpl() {
    }

    public void registrarLoginStrategy(Integer tipoUsuario, LoginStrategy loginStrategy) {
        loginStrategies.put(tipoUsuario, loginStrategy);
    }

    @Override
    public boolean login(Integer tipoUsuario, String userName, String senha) {
        LoginStrategy strategy = loginStrategies.get(tipoUsuario);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de usuário inválido.");
        }

        return strategy.login(userName, senha);
    }
}
