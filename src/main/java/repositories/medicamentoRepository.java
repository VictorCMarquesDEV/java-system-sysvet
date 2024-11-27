package repositories;

import exceptions.GenericException;
import models.Medicamentos;
import org.hibernate.SessionFactory;

public class medicamentoRepository extends GenericRepository<Medicamentos, Long>{

    public medicamentoRepository(SessionFactory sessionFactory) {
        super(Medicamentos.class, sessionFactory);
    }

    @Override
    public void save(Medicamentos entity) throws GenericException {
        // Executa o save padrão definido no GenericRepository
        super.save(entity);
    }
}
