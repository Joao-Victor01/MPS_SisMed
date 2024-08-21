package SisMed.service;

import SisMed.exception.AdminExistenteException;
import SisMed.exception.DadosAdminInvalidosException;
import SisMed.exception.DadosMedicoInvalidosException;
import SisMed.exception.ErroCadastroAdminException;
import SisMed.model.Admins;
import SisMed.repository.AdminsRepository;

import java.util.List;
import java.util.Optional;

public class AdminsService {

    AdminsRepository adminsRepository;

    public AdminsService(AdminsRepository adminsRepository) {
        this.adminsRepository = adminsRepository;
    }

    public void cadastrarAdmin(Admins admin) {
        try {
            Admins adminExistente = adminsRepository.buscarPorCpf(admin.getCpf()).orElse(null);
            if (adminExistente != null) {
                throw new AdminExistenteException("Administrador com CPF " + admin.getCpf() + " já está cadastrado.", adminExistente);
            }

            validarDadosAdmin(admin);

            adminsRepository.salvar(admin);
        } catch (AdminExistenteException | DadosAdminInvalidosException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroCadastroAdminException("Erro ao cadastrar Administrador.", e);
        }
    }

    public List<Admins> listarAdmins() {
        List<Admins> admins = adminsRepository.listarTodos();
        if(admins.isEmpty()){
            System.out.println("Nenhum administrador cadastrado.");
        }
        return admins;
    }

    private void validarDadosAdmin(Admins admin) {
        if (admin.getNome() == null || admin.getNome().isEmpty()) {
            throw new DadosMedicoInvalidosException("O nome do Administrador é obrigatório.");
        }
        if (admin.getCpf() == null) {
            throw new DadosAdminInvalidosException("O CPF do Administrador é obrigatório.");
        }

    }
}
