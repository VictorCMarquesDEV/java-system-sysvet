package repositories;

import models.Funcionario;
import models.Medicamentos;
import org.hibernate.SessionFactory;

public class medicamentoRepository extends GenericRepository<Medicamentos, Long>{

    private SessionFactory sessionFactory;

    public medicamentoRepository(SessionFactory sessionFactory) {
        super(Medicamentos.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}
