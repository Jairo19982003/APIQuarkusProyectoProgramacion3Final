package test.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Admin;
import test.Implementacion.IAdminRepo;

@ApplicationScoped
public class AdminRepository implements IAdminRepo {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void save(Admin admin) {em.persist(admin);}
}
