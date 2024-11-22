package repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import models.Proprietario;

public class ProprietarioRepository extends GenericRepository<Proprietario, Long> {
    private  SessionFactory sessionFactory;

    public ProprietarioRepository(SessionFactory sessionFactory) {
        super(Proprietario.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Proprietario findByCpf(String cpf) {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Proprietario WHERE cpf = :cpf", Proprietario.class)
                    .setParameter("cpf", cpf)
                    .uniqueResult();
        }
    }
}
