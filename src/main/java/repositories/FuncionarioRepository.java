package repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import exceptions.GenericException;
import models.Funcionario;

public class FuncionarioRepository extends GenericRepository<Funcionario, Long> {
    private SessionFactory sessionFactory;

    public FuncionarioRepository(SessionFactory sessionFactory) {
        super(Funcionario.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Funcionario entity)  throws GenericException {
        try (Session session = sessionFactory.openSession()) {
            Query<Funcionario> query = session.createNativeQuery("SELECT * FROM funcionarios WHERE cpf = :cpf",
                    Funcionario.class);
            query.setParameter("cpf", entity.getCpf());
            Funcionario funcionario = query.uniqueResult();

            if (funcionario == null) {
                super.save(entity);
            } else {
                throw new GenericException("Já existe um funcionário com este CPF!");
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
    }
}