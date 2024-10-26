package SisMed.model;

import SisMed.state.ConsultaAgendada;
import SisMed.state.EstadoConsulta;

import java.time.LocalDate;


public class Consultas {

    private Long id;
    private Long cpfPaciente;
    private Long cpfMedico;
    private LocalDate dataConsulta;
    private String descricao;
    private EstadoConsulta estado;

    public Consultas() {
        this.estado = new ConsultaAgendada(); // Estado inicial
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCpfMedico() {
        return cpfMedico;
    }

    public void setCpfMedico(Long cpfMedico) {
        this.cpfMedico = cpfMedico;
    }

    public Long getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(Long cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void confirmar() {
        estado.confirmar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void concluir() {
        estado.concluir(this);
    }

    public void setEstado(EstadoConsulta estado) {
        this.estado = estado;
    }

}
