package SisMed.model;

public class RelatorioAcesso {
    private long tipoUsuario;
    private String userName;
    private String dataAcesso;

    // Construtor
    public RelatorioAcesso(long tipoUsuario, String userName, String dataAcesso) {
        this.tipoUsuario = tipoUsuario;
        this.userName = userName;
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
