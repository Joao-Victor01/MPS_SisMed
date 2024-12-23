package SisMed.padroes.state;

import SisMed.model.Consultas;

public class ConsultaConcluida implements EstadoConsulta{

    @Override
    public void agendar(Consultas consulta) {
        System.out.println("Consulta já foi concluída e não pode ser reagendada.");
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
