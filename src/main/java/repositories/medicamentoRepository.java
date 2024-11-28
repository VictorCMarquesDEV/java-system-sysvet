package repositories;

import exceptions.GenericException;
import models.Funcionario;
import models.Medicamentos;
import models.Usuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class medicamentoRepository extends GenericRepository<Medicamentos, Long> {

    public medicamentoRepository(SessionFactory sessionFactory) {
        super(Medicamentos.class, sessionFactory);
    }

    @Override
    public void save(Medicamentos entity) throws GenericException {
        // Executa o save padrão definido no GenericRepository
        super.save(entity);
    }
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Medicamentos medicamento = session.find(Medicamentos.class, id);
            if (medicamento != null) {
                session.delete(medicamento);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
