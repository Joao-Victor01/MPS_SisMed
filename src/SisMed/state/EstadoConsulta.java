package SisMed.state;

import SisMed.model.Consultas;

public interface EstadoConsulta {
    void confirmar(Consultas consulta);
    void cancelar(Consultas consulta);
    void concluir(Consultas consulta);
}
