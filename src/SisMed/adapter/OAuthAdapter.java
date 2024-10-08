package SisMed.adapter;

import SisMed.login.LoginStrategy;

public class OAuthAdapter implements LoginStrategy {

    private final OAuthService oauthService;

    public OAuthAdapter(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @Override
    public boolean login(String userName, String senha) {
        return oauthService.authenticate(userName, senha);
    }
}
