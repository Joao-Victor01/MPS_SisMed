package SisMed.service;

import SisMed.exception.DadosConsultaInvalidosException;
import SisMed.model.Consultas;
import SisMed.model.Usuario;
import SisMed.repository.ConsultasRepositoryInterface;

import java.time.LocalDate;
import java.util.List;

public class ConsultasService {
    private final ConsultasRepositoryInterface consultasRepository;

    public ConsultasService(ConsultasRepositoryInterface consultasRepository) {
        this.consultasRepository = consultasRepository;
    }

    public void cadastrarConsulta(Consultas consulta) {
        validarDadosConsulta(consulta);
        consultasRepository.salvar(consulta);
    }

    public List<Consultas> listarConsultas() {
        return consultasRepository.listarTodos();
    }

    public void atualizarConsulta(Long idConsulta, LocalDate novaData, String novaDescricao) {
        consultasRepository.atualizar(idConsulta, novaData, novaDescricao);
    }

    public void removerConsulta(Long idConsulta) {
        consultasRepository.remover(idConsulta);
    }

    private void validarDadosConsulta(Consultas consulta) {
        if (consulta.getDataConsulta() == null) {
            throw new DadosConsultaInvalidosException("A data e hora da consulta são obrigatórias.");
        }
        if (consulta.getCpfMedico() == null) {
            throw new DadosConsultaInvalidosException("O médico responsável é obrigatório.");
        }
        if (consulta.getCpfPaciente() == null) {
            throw new DadosConsultaInvalidosException("O paciente é obrigatório.");
        }
        if (consulta.getDescricao() == null || consulta.getDescricao().isEmpty()) {
            throw new DadosConsultaInvalidosException("Os detalhes adicionais da consulta são obrigatórios.");
        }
    }

}
