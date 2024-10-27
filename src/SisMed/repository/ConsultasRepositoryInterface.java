package SisMed.repository;

import SisMed.model.Consultas;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsultasRepositoryInterface {
    void salvar(Consultas consulta);

    List<Consultas> listarTodos();

    void atualizar(Long idConsulta, LocalDate novaData, String novaDescricao);

    void remover(Long idConsulta);

    Consultas consultaPorId(Long idConsulta);
}
