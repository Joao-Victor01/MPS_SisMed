package com.SisMed.repository;

import com.SisMed.model.Consultas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultasRepository {
    private List<Consultas> consultasList = new ArrayList<>();


    public void salvar(Consultas consulta) {
        consultasList.add(consulta);
    }

    public List<Consultas> listarTodos() {
        return new ArrayList<>(consultasList);
    }

    public void atualizar(Long idConsulta, LocalDate novaData, String novaDescricao) {
        for (Consultas consulta : consultasList) {
            if (consulta.getId().equals(idConsulta)) {
                consulta.setDataConsulta(novaData);
                consulta.setDescricao(novaDescricao);
                System.out.println("Consulta atualizada localmente com sucesso!");
                return;
            }
        }
        System.out.println("Consulta não encontrada.");
    }

    public void remover(Long idConsulta) {
        consultasList.removeIf(consulta -> consulta.getId().equals(idConsulta));
        System.out.println("Consulta removida localmente com sucesso!");
    }

    public Optional<Consultas> consultaPorId(Long idConsulta) {
        for (Consultas consulta : consultasList) {
            if (consulta.getId().equals(idConsulta)) {
                return Optional.of(consulta);
            }
        }
        return Optional.empty();
    }

}
