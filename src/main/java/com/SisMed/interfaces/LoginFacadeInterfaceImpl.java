package com.SisMed.interfaces;

import com.SisMed.service.AdminsService;
import com.SisMed.service.MedicosService;
import com.SisMed.service.PacientesService;

public class LoginFacadeInterfaceImpl implements LoginFacadeInterface {

    private MedicosService medicoService;
    private PacientesService pacienteService;
    private AdminsService adminService;

    public LoginFacadeInterfaceImpl (MedicosService medicoService, PacientesService pacienteService, AdminsService adminService) {
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.adminService = adminService;
    }

    @Override
    public boolean login(Integer userType, String userName, String senha) {
        switch (userType) {
            case 1:
                return medicoService.loginMedico(userName, senha);
            case 2:
                return pacienteService.loginPaciente(userName, senha);
            case 3:
                return adminService.loginAdmin(userName, senha);
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
    }
}
