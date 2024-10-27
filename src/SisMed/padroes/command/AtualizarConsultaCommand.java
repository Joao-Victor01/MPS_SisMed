package SisMed.padroes.command;

import SisMed.service.ConsultasService;

import java.time.LocalDate;

public class AtualizarConsultaCommand implements Command {
    private ConsultasService consultasService;
    private Long idConsulta;
    private LocalDate novaData;
    private String novaDescricao;

    public AtualizarConsultaCommand(ConsultasService consultasService, Long idConsulta, LocalDate novaData, String novaDescricao) {
        this.consultasService = consultasService;
        this.idConsulta = idConsulta;
        this.novaData = novaData;
        this.novaDescricao = novaDescricao;
    }

    @Override
    public void execute() {
        consultasService.atualizarConsulta(idConsulta, novaData, novaDescricao);
    }
}
