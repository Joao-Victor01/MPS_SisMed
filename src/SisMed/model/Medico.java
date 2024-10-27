package SisMed.model;

import SisMed.interfaces.MedicoInterface;

public class Medico extends Usuario implements MedicoInterface {
    private String crm;
    private String especializacoes;

    @Override
    public String getCrm() {
        return crm;
    }

    @Override
    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String getEspecializacoes() {
        return especializacoes;
    }

    @Override
    public void setEspecializacoes(String especializacoes) {
        this.especializacoes = especializacoes;
    }

    @Override
    public Integer getTipoUsuario() {
        return 1;
    }
}