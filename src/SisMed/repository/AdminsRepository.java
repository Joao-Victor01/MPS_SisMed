package SisMed.repository;

import SisMed.model.Admins;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdminsRepository {

    private List<Admins> admins = new ArrayList<>();

    public void salvar(Admins admin) {
        admins.add(admin);
    }

    public List<Admins> listarTodos() {
        return new ArrayList<>(admins);
    }

    public Optional<Admins> buscarPorCpf(Long cpf) {
        return admins.stream()
                .filter(p -> Objects.equals(p.getCpf(), cpf))
                .findFirst();
    }
}
