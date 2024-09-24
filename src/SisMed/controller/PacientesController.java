package SisMed.controller;

import SisMed.exception.DadosPacienteInvalidosException;
import SisMed.exception.ErroCadastroPacienteException;
import SisMed.exception.PacienteExistenteException;
import SisMed.model.Pacientes;
import SisMed.service.PacientesService;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

public class PacientesController {
    private PacientesService pacientesService;

    public PacientesController(PacientesService pacienteService){
        this.pacientesService = pacienteService;
    }

    public void cadastrarPaciente(String nome, Long cpf, String endereco, String sexo, LocalDate dataNascimento, String login, String senha){
        try {
            Pacientes novoPaciente = new Pacientes();
            novoPaciente.setNome(nome);
            novoPaciente.setCpf(cpf);
            novoPaciente.setSexo(sexo);
            novoPaciente.setEndereco(endereco);
            novoPaciente.setDataNascimento(dataNascimento);
            novoPaciente.setLogin(login);
            novoPaciente.setSenha(senha);

            pacientesService.cadastrarPaciente(novoPaciente);
        } catch (PacienteExistenteException e) {
            Pacientes pacienteExistente = e.getPacienteExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados do paciente já cadastrado:");
            System.err.println("Nome: " + pacienteExistente.getNome());
            System.err.println("CPF: " + pacienteExistente.getCpf());

        } catch (DadosPacienteInvalidosException e) {
            System.err.println("Erro: Dados inválidos. " + e.getMessage());

        } catch (ErroCadastroPacienteException e) {
            System.err.println("Erro ao cadastrar paciente. " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cadastrarPacienteDb(String nome, Long cpf, String endereco, String sexo, LocalDate dataNascimento, String login, String senha){
        try {
            Pacientes novoPaciente = new Pacientes();
            novoPaciente.setNome(nome);
            novoPaciente.setCpf(cpf);
            novoPaciente.setSexo(sexo);
            novoPaciente.setEndereco(endereco);
            novoPaciente.setDataNascimento(dataNascimento);
            novoPaciente.setLogin(login);
            novoPaciente.setSenha(senha);

            pacientesService.cadastrarPacienteDb(novoPaciente);
        } catch (PacienteExistenteException e) {
            Pacientes pacienteExistente = e.getPacienteExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados do paciente já cadastrado:");
            System.err.println("Nome: " + pacienteExistente.getNome());
            System.err.println("CPF: " + pacienteExistente.getCpf());

        } catch (DadosPacienteInvalidosException e) {
            System.err.println("Erro: Dados inválidos. " + e.getMessage());

        } catch (ErroCadastroPacienteException e) {
            System.err.println("Erro ao cadastrar paciente. " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void listarPacientes() {
        List<Pacientes> listaPacientes = pacientesService.listarPacientes();
        System.out.println("---- Pacientes armazenados localmente: ----");
        for (Pacientes paciente : listaPacientes) {
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("CPF: " + paciente.getCpf());
            System.out.println("Endereço: " + paciente.getEndereco());
            System.out.println("Sexo: " + paciente.getSexo());
            System.out.println("Data de Nascimento: " + paciente.getDataNascimento());
            System.out.println("-----------------------------------");
        }

        List<Pacientes> listaPacientesDb = pacientesService.listarPacientesDb();
        System.out.println("---- Pacientes da base de dados: ---- ");
        for (Pacientes paciente : listaPacientesDb) {
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("CPF: " + paciente.getCpf());
            System.out.println("Endereço: " + paciente.getEndereco());
            System.out.println("Sexo: " + paciente.getSexo());
            System.out.println("Data de Nascimento: " + paciente.getDataNascimento());
            System.out.println("-----------------------------------");
        }
    }

}
