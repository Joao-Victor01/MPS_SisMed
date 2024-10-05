package SisMed.interfaces;

import SisMed.model.Pacientes;
import java.util.List;
import java.util.Optional;

public interface PacientesRepository {
    void salvar(Pacientes paciente);
    Optional<Pacientes> buscarPorCpf(Long cpf);
    List<Pacientes> listarTodos();
}
