package SisMed.service;

import SisMed.exception.DadosMedicoInvalidosException;
import SisMed.exception.ErroCadastroMedicoException;
import SisMed.exception.MedicoExistenteException;
import SisMed.model.Medicos;
import SisMed.repository.MedicosRepository;

import java.util.List;

public class MedicosService {

    MedicosRepository medicosRepository;
    public MedicosService(MedicosRepository medicosRepository){
        this.medicosRepository = medicosRepository;
    }

    public void cadastrarMedico (Medicos medico){
        try {
            Medicos medicoExistente = medicosRepository.buscarPorCpf(medico.getCpf()).orElse(null);
            if (medicoExistente != null) {
                throw new MedicoExistenteException("Medico com CPF " + medico.getCpf() + " já está cadastrado.", medicoExistente);
            }

            validarDadosMedico(medico);

            medicosRepository.salvar(medico);
        } catch (MedicoExistenteException | DadosMedicoInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroMedicoException("Erro ao cadastrar médico.", e);
        }
    }

    public List<Medicos> listarMedicos() {
        List<Medicos> medicos = medicosRepository.listarTodos();
        if(medicos.isEmpty()){
            System.out.println("Nenhum médico cadastrado.");
        }
        return medicos;
    }


    private void validarDadosMedico(Medicos medico) {
        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new DadosMedicoInvalidosException("O nome do médico é obrigatório.");
        }
        if (medico.getCpf() == null) {
            throw new DadosMedicoInvalidosException("O CPF do médico é obrigatório.");
        }
        if(medico.getCrm() == null || medico.getCrm().isEmpty()){
            throw new DadosMedicoInvalidosException("O CRM do médico é obrigatório.");
        }
    }
}
