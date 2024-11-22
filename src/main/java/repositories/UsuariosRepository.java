package repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import exceptions.GenericException;
import models.Usuarios;

public class UsuariosRepository extends GenericRepository<Usuarios, Long> {
    private SessionFactory sessionFactory;

    public UsuariosRepository(SessionFactory sessionFactory) {
        super(Usuarios.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Usuarios entity) throws GenericException {
        try (Session session = sessionFactory.openSession()) {
            Query<Usuarios> query = session.createNativeQuery(
                "SELECT * FROM usuarios WHERE username = :username",
                Usuarios.class
            );
            query.setParameter("username", entity.getUsername());
            Usuarios usuario = query.uniqueResult();

            if (usuario == null) {
                super.save(entity);
            } else {
                throw new GenericException("Já existe um usuário com este username!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Usuarios findByField(String fieldName, String value) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Usuarios where " + fieldName + " = :value";
            return session.createQuery(hql, Usuarios.class)
                          .setParameter("value", value)
                          .uniqueResult();
        }
    }
}
