package SisMed.interfaces;

public interface RepositoryFactory {
    AdminsRepository createAdminsRepository();
    MedicosRepository createMedicosRepository();
    PacientesRepository createPacientesRepository();
}
