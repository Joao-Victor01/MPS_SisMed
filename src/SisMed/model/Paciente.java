package SisMed.model;

import SisMed.interfaces.PacienteInterface;

public class Paciente extends Usuario implements PacienteInterface {
    private String ficha;
    private String historicoMedico;

    @Override
    public String getFicha() {
        return ficha;
    }

    @Override
    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    @Override
    public String getHistoricoMedico() {
        return historicoMedico;
    }

    @Override
    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    @Override
    public Integer getTipoUsuario() {
        return 2;
    }
}

