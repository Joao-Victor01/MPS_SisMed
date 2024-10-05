package SisMed.interfaces;

import SisMed.model.Admins;

import java.util.List;
import java.util.Optional;

public interface AdminsRepository {
    void salvar(Admins admin);
    Optional<Admins> buscarPorCpf(Long cpf);
    List<Admins> listarTodos();
}
