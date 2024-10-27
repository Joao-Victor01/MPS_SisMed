package SisMed.padroes.command;

import SisMed.model.Consultas;
import SisMed.service.ConsultasService;

import java.time.LocalDate;

public class CadastrarConsultaCommand implements Command {
    private ConsultasService consultasService;
    private Long cpfPaciente;
    private Long cpfMedico;
    private LocalDate dataConsulta;
    private String descricao;

    public CadastrarConsultaCommand(ConsultasService consultasService, Long cpfPaciente, Long cpfMedico, LocalDate dataConsulta, String descricao) {
        this.consultasService = consultasService;
        this.cpfPaciente = cpfPaciente;
        this.cpfMedico = cpfMedico;
        this.dataConsulta = dataConsulta;
        this.descricao = descricao;
    }

    @Override
    public void execute() {
        Consultas consulta = new Consultas();
        consulta.setCpfPaciente(cpfPaciente);
        consulta.setCpfMedico(cpfMedico);
        consulta.setDataConsulta(dataConsulta);
        consulta.setDescricao(descricao);

        consultasService.cadastrarConsulta(consulta);
    }
}
