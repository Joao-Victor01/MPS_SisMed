package SisMed.state;

import SisMed.model.Consultas;

public class ConsultaConfirmada implements EstadoConsulta{
    @Override
    public void confirmar(Consultas consulta) {
        System.out.println("Consulta já está confirmada.");
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
