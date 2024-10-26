package SisMed.state;

import SisMed.model.Consultas;

public class ConsultaConcluida implements EstadoConsulta{
    @Override
    public void confirmar(Consultas consulta) {
        System.out.println("A consulta já foi concluída e não pode ser confirmada novamente.");
    }

    @Override
    public void cancelar(Consultas consulta) {
        System.out.println("A consulta já foi concluída e não pode ser cancelada.");
    }

    @Override
    public void concluir(Consultas consulta) {
        System.out.println("A consulta já está concluída.");
    }
}
