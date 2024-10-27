package SisMed.service;

import SisMed.model.Relatorio;
import SisMed.repository.RelatorioRepositoryInterface;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioService {

    private final RelatorioRepositoryInterface relatorioRepository;

    public RelatorioService(RelatorioRepositoryInterface relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public void registrarAcesso(Relatorio relatorio) {
        relatorioRepository.salvar(relatorio);
    }

    public List<Relatorio> listarRelatorio() {
        return relatorioRepository.listarRelatorio();
    }


    public String formatarData(String dataAcesso) {

       try {
           Timestamp timestamp = Timestamp.valueOf(dataAcesso);
           LocalDateTime localDateTime = timestamp.toLocalDateTime();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

           return localDateTime.format(formatter);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return "";
    }

    public String setUserType(long userType) {
        return switch ((int) userType) {
            case 1 -> "Médico";
            case 2 -> "Paciente";
            case 3 -> "Adminstrador";
            default -> "";
        };
    }

}