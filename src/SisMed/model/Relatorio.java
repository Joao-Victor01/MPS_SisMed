package SisMed.model;

public class Relatorio {
    private long tipoUsuario;
    private String userName;
    private String dataAcesso;

    // Construtor
//    public Relatorio(long tipoUsuario, String userName, String dataAcesso) {
//        this.tipoUsuario = tipoUsuario;
//        this.userName = userName;
//        this.dataAcesso = dataAcesso;
//    }

    public void setTipoUsuario(long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDataAcesso(String dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    // Getters
    public long getTipoUsuario() {
        return tipoUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public String getDataAcesso() {
        return dataAcesso;
    }
}
