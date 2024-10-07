package SisMed.interfaces;


public class RepositoryFactoryImpl implements RepositoryFactory {
    @Override
    public AdminsRepository createAdminsRepository() {
        return new AdminsRepository();
    }

    @Override
    public MedicosRepository createMedicosRepository() {
        return new MedicosRepository();
    }

    @Override
    public PacientesRepository createPacientesRepository() {
        return new PacientesRepository();
    }
}