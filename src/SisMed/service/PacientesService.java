package SisMed.service;

import SisMed.exception.DadosPacienteInvalidosException;
import SisMed.exception.ErroCadastroPacienteException;
import SisMed.exception.PacienteExistenteException;
import SisMed.model.Pacientes;
import SisMed.repository.PacientesRepository;

import java.util.List;

public class PacientesService {
    private PacientesRepository pacientesRepository;

    public PacientesService(PacientesRepository pacientesRepository){
        this.pacientesRepository = pacientesRepository;
    }

    public void cadastrarPaciente (Pacientes paciente){
        try {
            Pacientes pacienteExistente = pacientesRepository.buscarPorCpf(paciente.getCpf()).orElse(null);
            if (pacienteExistente != null) {
                throw new PacienteExistenteException("Paciente com CPF " + paciente.getCpf() + " já está cadastrado.", pacienteExistente);
            }

            validarDadosPaciente(paciente);

            pacientesRepository.salvar(paciente);
        } catch (PacienteExistenteException | DadosPacienteInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroPacienteException("Erro ao cadastrar paciente.", e);
        }
    }

    public List<Pacientes> listarPacientes() {
        List<Pacientes> pacientes = pacientesRepository.listarTodos();
        if(pacientes.isEmpty()){
            System.out.println("Nenhum paciente cadastrado.");
        }
        return pacientes;
    }

    private void validarDadosPaciente(Pacientes paciente) {
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new DadosPacienteInvalidosException("O nome do paciente é obrigatório.");
        }
        if (paciente.getCpf() == null) {
            throw new DadosPacienteInvalidosException("O CPF do paciente é obrigatório.");
        }
    }
}
