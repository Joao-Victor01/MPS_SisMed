package SisMed.padroes.facade;

import SisMed.padroes.command.Command;
import java.util.ArrayList;
import java.util.List;

public class SistemaFacade {
    private List<Command> commandHistory = new ArrayList<>();

    public void executarComando(Command comando) {
        comando.execute();
        commandHistory.add(comando);
    }

    public List<Command> getCommandHistory() {
        return commandHistory;
    }
}
