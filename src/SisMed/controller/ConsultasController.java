package SisMed.controller;

import SisMed.exception.*;
import SisMed.model.Consultas;
import SisMed.service.ConsultasService;

import java.sql.SQLException;
import java.time.LocalDate;

public class ConsultasController {
    private ConsultasService consultasService;

    public ConsultasController(ConsultasService consultasService) {
        this.consultasService = consultasService;
    }

    public void cadastrarConsulta(Long cpfPaciente, Long cpfMedico, LocalDate dataConsulta, String descricao) {
        try {
            Consultas novaConsulta = new Consultas();
            novaConsulta.setCpfPaciente(cpfPaciente);
            novaConsulta.setCpfMedico(cpfMedico);
            novaConsulta.setDataConsulta(dataConsulta);
            novaConsulta.setDescricao(descricao);

            consultasService.cadastrarConsulta(novaConsulta);
        } catch (ConsultaExistenteException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (ErroCadastroConsultaException e) {
            System.err.println("Erro ao cadastrar consulta. " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void atualizarConsulta(Long idConsulta, LocalDate novaData, String novaDescricao) {
        try {
            consultasService.atualizarConsulta(idConsulta, novaData, novaDescricao);
            System.out.println("Consulta atualizada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarConsultas() {

        if (consultasService.listarConsultas().isEmpty()){
            System.out.println("Nenhuma consulta cadastrado!");
        }else {
            consultasService.listarConsultas().forEach(consultas -> {
                System.out.println("CPF Paciente: " + consultas.getCpfPaciente());
                System.out.println("CPF Médico: " + consultas.getCpfMedico());
                System.out.println("Data da consulta: " + consultas.getDataConsulta());
                System.out.println("Descrição: " + consultas.getDescricao());
                System.out.println("-----------------------------------");
            });
        }
    }


    public void removerConsulta(Long id) {
            consultasService.removerConsulta(id);
    }

    public void confirmarConsulta(Consultas consulta) {
        consultasService.confirmarConsulta(consulta);
    }

    public void cancelarConsulta(Consultas consulta) {
        consultasService.cancelarConsulta(consulta);
    }

    public void concluirConsulta(Consultas consulta) {
        consultasService.concluirConsulta(consulta);
    }
}
