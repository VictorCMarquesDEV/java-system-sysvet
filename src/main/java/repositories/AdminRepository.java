package repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import exceptions.GenericException;
import models.Admin;

public class AdminRepository extends GenericRepository<Admin, Long> {
    private SessionFactory sessionFactory;

    public AdminRepository(SessionFactory sessionFactory) {
        super(Admin.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Admin entity) throws GenericException {
        try (Session session = sessionFactory.openSession()) {
            Query<Admin> query = session.createNativeQuery(
                "SELECT * FROM admin WHERE username = :username", 
                Admin.class
            );
            query.setParameter("username", entity.getUsername());
            Admin admin = query.uniqueResult();

            if (admin == null) {
                super.save(entity);
            } else {
                throw new GenericException("Já existe um administrador com este username!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Admin findByField(String fieldName, String value) {
    try (Session session = sessionFactory.openSession()) {
        String hql = "from Admin where " + fieldName + " = :value";
        return session.createQuery(hql, Admin.class)
                      .setParameter("value", value)
                      .uniqueResult();
    }
}
}
