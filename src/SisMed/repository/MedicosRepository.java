package SisMed.repository;

import SisMed.model.Medicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MedicosRepository implements SisMed.interfaces.MedicosRepository {

    private final List<Medicos> medicos = new ArrayList<>();

    public void salvar(Medicos medico) {
        medicos.add(medico);
    }

    public List<Medicos> listarTodos() {
        return new ArrayList<>(medicos);
    }

    public Optional<Medicos> buscarPorCpf(Long cpf) {
        return medicos.stream()
                .filter(p -> Objects.equals(p.getCpf(), cpf))
                .findFirst();
    }
}
