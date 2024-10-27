package SisMed.padroes.state;

import SisMed.model.Consultas;

public interface EstadoConsulta {
    void agendar(Consultas consulta);
    void cancelar(Consultas consulta);
    void concluir(Consultas consulta);
}
