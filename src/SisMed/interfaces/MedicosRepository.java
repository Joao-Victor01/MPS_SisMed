package SisMed.interfaces;

import SisMed.model.Medicos;
import java.util.List;
import java.util.Optional;

public interface MedicosRepository {
    void salvar(Medicos medico);
    Optional<Medicos> buscarPorCpf(Long cpf);
    List<Medicos> listarTodos();
}
