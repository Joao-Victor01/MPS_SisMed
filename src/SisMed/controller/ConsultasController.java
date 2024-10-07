package SisMed.controller;

import SisMed.exception.DadosConsultaInvalidosException;
import SisMed.exception.ConsultaExistenteException;
import SisMed.exception.ErroCadastroConsultaException;
import SisMed.model.Consultas;
import SisMed.service.ConsultasService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
            Consultas consultaExistente = e.getConsultaExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados da consulta já cadastrada:");
            System.err.println("Paciente: " + consultaExistente.getCpfPaciente());
            System.err.println("Médico: " + consultaExistente.getCpfMedico());
        } catch (DadosConsultaInvalidosException e) {
            System.err.println("Erro: Dados da consulta inválidos. " + e.getMessage());
        } catch (ErroCadastroConsultaException e) {
            System.err.println("Erro ao cadastrar consulta. " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cadastrarConsultaDb(Long cpfPaciente, Long cpfMedico, LocalDate dataConsulta, String descricao) {
        try {
            Consultas novaConsulta = new Consultas();
            novaConsulta.setCpfPaciente(cpfPaciente);
            novaConsulta.setCpfMedico(cpfMedico);
            novaConsulta.setDataConsulta(dataConsulta);
            novaConsulta.setDescricao(descricao);

            consultasService.cadastrarConsultaDb(novaConsulta);
        } catch (ConsultaExistenteException e) {
            Consultas consultaExistente = e.getConsultaExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados da consulta já cadastrada:");
            System.err.println("Paciente: " + consultaExistente.getCpfPaciente());
            System.err.println("Médico: " + consultaExistente.getCpfMedico());
        } catch (DadosConsultaInvalidosException e) {
            System.err.println("Erro: Dados da consulta inválidos. " + e.getMessage());
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

        } catch (DadosConsultaInvalidosException e) {
            System.err.println("Erro: " + e.getMessage());

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void atualizarConsultaDb(Long idConsulta, LocalDate novaData, String novaDescricao) {
        try {
            consultasService.atualizarConsultaDb(idConsulta, novaData, novaDescricao);
            System.out.println("Consulta na base de dados atualizada com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarConsultas() {
            List<Consultas> listaConsultasDb = consultasService.listarConsultasDb();
            System.out.println("---- Consultas da base de dados: ---- ");
            callListConsultas(listaConsultasDb);
    }

    public void removerConsulta(Long id){
        try {
            consultasService.removerConsulta(id);
        } catch (DadosConsultaInvalidosException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public void removerConsultaDb(Long id){
        try {
            consultasService.removerConsultaDb(id);
        } catch (DadosConsultaInvalidosException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private void callListConsultas(List<Consultas> listaConsultas) {
        for (Consultas consulta : listaConsultas) {
            System.out.println("Paciente (CPF): " + consulta.getCpfPaciente());
            System.out.println("Médico (CPF): " + consulta.getCpfMedico());
            System.out.println("Data da Consulta: " + consulta.getDataConsulta());
            System.out.println("Hora da Consulta: " + consulta.getDataConsulta());
            System.out.println("Descrição: " + consulta.getDescricao());
            System.out.println("-----------------------------------");
        }
    }


}
