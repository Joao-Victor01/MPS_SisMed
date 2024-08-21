package SisMed.repository;

import SisMed.model.Pacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PacientesRepository {

    private List<Pacientes> pacientes = new ArrayList<>();

    public void salvar(Pacientes paciente) {
        pacientes.add(paciente);
    }

    public List<Pacientes> listarTodos() {
        return new ArrayList<>(pacientes);
    }

    public Optional<Pacientes> buscarPorCpf(Long cpf) {
        return pacientes.stream()
                .filter(p -> Objects.equals(p.getCpf(), cpf))
                .findFirst();
    }
}
