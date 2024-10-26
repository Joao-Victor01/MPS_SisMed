package SisMed.state;

import SisMed.model.Consultas;

public class ConsultaAgendada implements EstadoConsulta{
    @Override
    public void confirmar(Consultas consulta) {
        consulta.setEstado(new ConsultaConfirmada());
        System.out.println("Consulta confirmada.");
    }

    @Override
    public void cancelar(Consultas consulta) {
        consulta.setEstado(new ConsultaCancelada());
        System.out.println("Consulta cancelada.");
    }

    @Override
    public void concluir(Consultas consulta) {
        System.out.println("Não é possível concluir uma consulta que ainda não foi confirmada.");
    }
}
