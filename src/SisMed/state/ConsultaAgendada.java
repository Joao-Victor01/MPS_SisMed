package SisMed.state;

import SisMed.model.Consultas;

public class ConsultaAgendada implements EstadoConsulta{
    @Override
    public void agendar(Consultas consulta) {
        System.out.println("Consulta já está agendada.");
    }

    @Override
    public void cancelar(Consultas consulta) {
        consulta.setEstado(new ConsultaCancelada());
        System.out.println("Consulta cancelada.");
    }

    @Override
    public void concluir(Consultas consulta) {
        consulta.setEstado(new ConsultaConcluida());
        System.out.println("Consulta concluída com sucesso.");
    }
}
