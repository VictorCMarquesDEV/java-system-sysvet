package repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import exceptions.GenericException;
import models.Consulta;

public class ConsultasRepository extends GenericRepository<Consulta, Long> {
    @SuppressWarnings("unused")
    private SessionFactory sessionFactory;

    public ConsultasRepository(SessionFactory sessionFactory) {
        super(Consulta.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Consulta entity) throws GenericException {
        try (Session session = sessionFactory.openSession()) {
            Query<Consulta> query = session.createNativeQuery(
                    "SELECT * FROM consultas WHERE data = :data AND hora = :hora",
                    Consulta.class);
            query.setParameter("data", entity.getData());
            query.setParameter("hora", entity.getHora());
            Consulta consulta = query.uniqueResult();

            if (consulta == null) {
                super.save(entity);
            } else {
                throw new GenericException("Já existe uma consulta marcada para esse horário!");
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
    }
}