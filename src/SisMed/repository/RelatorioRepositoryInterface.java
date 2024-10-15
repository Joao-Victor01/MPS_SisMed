package SisMed.repository;

import SisMed.model.Relatorio;

import java.util.List;
import java.util.Optional;

public interface RelatorioRepositoryInterface {

    void salvar(Relatorio relatorio);

    List<Relatorio> listarRelatorio();

    Optional<Relatorio> buscarRelatorioPorId(Long id);

}
