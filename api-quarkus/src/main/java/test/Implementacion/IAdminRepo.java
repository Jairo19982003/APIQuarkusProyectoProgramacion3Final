package test.Implementacion;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Admin;

public interface IAdminRepo{
    public void save(Admin admin);
}
