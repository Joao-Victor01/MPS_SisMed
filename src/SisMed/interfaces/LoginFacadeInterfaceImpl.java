package SisMed.interfaces;

import SisMed.service.AdminsService;
import SisMed.service.MedicosService;
import SisMed.service.PacientesService;

public class LoginFacadeInterfaceImpl implements LoginFacadeInterface {

    private final MedicosService medicoService;
    private final PacientesService pacienteService;
    private final AdminsService adminService;

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
