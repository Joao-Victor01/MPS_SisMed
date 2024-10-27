package SisMed.padroes.state;

import SisMed.model.Consultas;

public class ConsultaCancelada implements EstadoConsulta{

    @Override
    public void agendar(Consultas consulta) {
        consulta.setEstado(new ConsultaAgendada());
        System.out.println("Consulta foi reagendada.");
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
