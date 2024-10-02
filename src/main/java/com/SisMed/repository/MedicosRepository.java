package com.SisMed.repository;

import com.SisMed.model.Medicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MedicosRepository {

    private List<Medicos> medicos = new ArrayList<>();

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
