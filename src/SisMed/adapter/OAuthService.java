package SisMed.adapter;

public class OAuthService {
    public boolean authenticate(String email, String password) {
        System.out.println("Autenticado via OAuth.");
        return true;
    }
}
