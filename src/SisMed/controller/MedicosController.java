package SisMed.controller;

import SisMed.exception.DadosMedicoInvalidosException;
import SisMed.exception.ErroCadastroMedicoException;
import SisMed.exception.MedicoExistenteException;
import SisMed.model.Medicos;
import SisMed.service.MedicosService;

import java.time.LocalDate;
import java.util.List;

public class MedicosController {
    private MedicosService medicosService;

    public MedicosController(MedicosService medicosService){
        this.medicosService = medicosService;
    }

    public void cadastrarMedico(String nome, Long cpf, String crm, String especializacoes,
                                String endereco, String sexo, LocalDate dataNascimento,  String login, String senha){
        try {
            Medicos novoMedico = new Medicos();
            novoMedico.setNome(nome);
            novoMedico.setCpf(cpf);
            novoMedico.setSexo(sexo);
            novoMedico.setEndereco(endereco);
            novoMedico.setDataNascimento(dataNascimento);
            novoMedico.setCrm(crm);
            novoMedico.setEspecializacoes(especializacoes);
            novoMedico.setLogin(login);
            novoMedico.setSenha(senha);

            medicosService.cadastrarMedico(novoMedico);
        } catch (MedicoExistenteException e) {
            Medicos medicoExostente = e.getMedicoExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados do médico já cadastrado:");
            System.err.println("Nome: " + medicoExostente.getNome());
            System.err.println("CPF: " + medicoExostente.getCpf());

        } catch (DadosMedicoInvalidosException e) {
            System.err.println("Erro: Dados inválidos. " + e.getMessage());

        } catch (ErroCadastroMedicoException e) {
            System.err.println("Erro ao cadastrar médico. " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cadastrarMedicoDb(String nome, Long cpf, String crm, String especializacoes,
                                String endereco, String sexo, LocalDate dataNascimento,  String login, String senha){
        try {
            Medicos novoMedico = new Medicos();
            novoMedico.setNome(nome);
            novoMedico.setCpf(cpf);
            novoMedico.setSexo(sexo);
            novoMedico.setEndereco(endereco);
            novoMedico.setDataNascimento(dataNascimento);
            novoMedico.setCrm(crm);
            novoMedico.setEspecializacoes(especializacoes);
            novoMedico.setLogin(login);
            novoMedico.setSenha(senha);

            medicosService.cadastrarMedicoDb(novoMedico);
        } catch (MedicoExistenteException e) {
            Medicos medicoExostente = e.getMedicoExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados do médico já cadastrado:");
            System.err.println("Nome: " + medicoExostente.getNome());
            System.err.println("CPF: " + medicoExostente.getCpf());

        } catch (DadosMedicoInvalidosException e) {
            System.err.println("Erro: Dados inválidos. " + e.getMessage());

        } catch (ErroCadastroMedicoException e) {
            System.err.println("Erro ao cadastrar médico. " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void listarMedicos() {
        List<Medicos> listaMedicos = medicosService.listarMedicos();
        System.out.println("---- Médicos armazenados localmente: ----");
        for (Medicos medico : listaMedicos) {
            System.out.println("Nome: " + medico.getNome());
            System.out.println("CPF: " + medico.getCpf());
            System.out.println("CRM: " + medico.getCrm());
            System.out.println("Endereço: " + medico.getEndereco());
            System.out.println("Sexo: " + medico.getSexo());
            System.out.println("Data de Nascimento: " + medico.getDataNascimento());
            System.out.println("Especializações: " + medico.getEspecializacoes());
            System.out.println("-----------------------------------");
        }

        List<Medicos> listaMedicosDb = medicosService.listarMedicosDb();
        System.out.println("---- Médicos da base de dados: ---- ");
        for (Medicos medico : listaMedicosDb) {
            System.out.println("Nome: " + medico.getNome());
            System.out.println("CPF: " + medico.getCpf());
            System.out.println("CRM: " + medico.getCrm());
            System.out.println("Endereço: " + medico.getEndereco());
            System.out.println("Sexo: " + medico.getSexo());
            System.out.println("Data de Nascimento: " + medico.getDataNascimento());
            System.out.println("Especializações: " + medico.getEspecializacoes());
            System.out.println("-----------------------------------");
        }
    }
}
