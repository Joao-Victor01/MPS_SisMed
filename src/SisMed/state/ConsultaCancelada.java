package SisMed.state;

import SisMed.model.Consultas;

public class ConsultaCancelada implements EstadoConsulta{
    @Override
    public void confirmar(Consultas consulta) {
        System.out.println("A consulta foi cancelada e não pode ser confirmada.");
    }

    @Override
    public void cancelar(Consultas consulta) {
        System.out.println("A consulta já está cancelada.");
    }

    @Override
    public void concluir(Consultas consulta) {
        System.out.println("A consulta foi cancelada e não pode ser concluída.");
    }
}
