package SisMed.controller;

import SisMed.exception.*;
import SisMed.model.Admins;
import SisMed.model.Pacientes;
import SisMed.service.AdminsService;

import java.time.LocalDate;
import java.util.List;

public class AdminsController {
    private final AdminsService adminsService;

    public AdminsController(AdminsService adminsService) {
        this.adminsService = adminsService;
    }

    public void cadastrarAdmin(String nome, Long cpf, String endereco, String sexo, LocalDate dataNascimento, String userName, String senha) {
        try {
            Admins novoAdmin = new Admins();
            novoAdmin.setNome(nome);
            novoAdmin.setCpf(cpf);
            novoAdmin.setSexo(sexo);
            novoAdmin.setEndereco(endereco);
            novoAdmin.setDataNascimento(dataNascimento);
            novoAdmin.setUserName(userName);
            novoAdmin.setSenha(senha);

            adminsService.cadastrarAdmin(novoAdmin);
        } catch (AdminExistenteException e) {
            Admins adminExistente = e.getAdminExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados do Administrador já cadastrado:");
            System.err.println("Nome: " + adminExistente.getNome());
            System.err.println("CPF: " + adminExistente.getCpf());

        } catch (DadosAdminInvalidosException e) {
            System.err.println("Erro: Dados inválidos. " + e.getMessage());

        } catch (ErroCadastroAdminException e) {
            System.err.println("Erro ao cadastrar Administrador. " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cadastrarAdminDb(String nome, Long cpf, String endereco, String sexo, LocalDate dataNascimento, String userName, String senha) {
        try {
            Admins novoAdmin = new Admins();
            novoAdmin.setNome(nome);
            novoAdmin.setCpf(cpf);
            novoAdmin.setSexo(sexo);
            novoAdmin.setEndereco(endereco);
            novoAdmin.setDataNascimento(dataNascimento);
            novoAdmin.setUserName(userName);
            novoAdmin.setSenha(senha);

            adminsService.cadastrarAdminDb(novoAdmin);
        } catch (AdminExistenteException e) {
            Admins adminExistente = e.getAdminExistente();
            System.err.println("Erro: " + e.getMessage());
            System.err.println("Dados do Administrador já cadastrado:");
            System.err.println("Nome: " + adminExistente.getNome());
            System.err.println("CPF: " + adminExistente.getCpf());

        } catch (DadosAdminInvalidosException e) {
            System.err.println("Erro: Dados inválidos. " + e.getMessage());

        } catch (ErroCadastroAdminException e) {
            System.err.println("Erro ao cadastrar Administrador. " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarAdmins() {
        List<Admins> listaAdmins = adminsService.listarAdmins();
        System.out.println("---- Administradores armazenados localmente: ----");
        for (Admins admin : listaAdmins) {
            System.out.println("Nome: " + admin.getNome());
            System.out.println("CPF: " + admin.getCpf());
            System.out.println("Endereço: " + admin.getEndereco());
            System.out.println("Sexo: " + admin.getSexo());
            System.out.println("Data de Nascimento: " + admin.getDataNascimento());
            System.out.println("-----------------------------------");
        }

        List<Admins> listaAdminsDb = adminsService.listarAdminsDb();
        System.out.println("---- Administradores da base de dados: ---- ");
        for (Admins admin : listaAdminsDb) {
            System.out.println("Nome: " + admin.getNome());
            System.out.println("CPF: " + admin.getCpf());
            System.out.println("Endereço: " + admin.getEndereco());
            System.out.println("Sexo: " + admin.getSexo());
            System.out.println("Data de Nascimento: " + admin.getDataNascimento());
            System.out.println("-----------------------------------");
        }
    }

}
