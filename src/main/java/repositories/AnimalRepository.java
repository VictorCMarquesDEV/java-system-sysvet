package repositories;

import exceptions.GenericException;  // Importando a nova exceção genérica
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import models.Animal;

public class AnimalRepository extends GenericRepository<Animal, Long> {
    private final SessionFactory sessionFactory;

    public AnimalRepository(SessionFactory sessionFactory) {
        super(Animal.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Animal entity) throws GenericException {  // Alterando para GenericException
        try (Session session = sessionFactory.openSession()) {
            // Verificação se o animal já existe no banco
            String sql = "SELECT * FROM animal WHERE nome = :nomeAnimal AND especie = :especieAnimal " +
                    "AND raca = :racaAnimal AND data_nascimento = :dataNascimento AND proprietario_id = :id";

            Query<Animal> query = session.createNativeQuery(sql, Animal.class);
            query.setParameter("nomeAnimal", entity.getNome());
            query.setParameter("especieAnimal", entity.getEspecie());
            query.setParameter("racaAnimal", entity.getRaca());
            query.setParameter("dataNascimento", entity.getDataNascimento().toString());
            query.setParameter("id", entity.getProprietario().getId());

            Animal existingAnimal = query.uniqueResult();

            if (existingAnimal == null) {
                // Se o animal não existir, salvamos no banco de dados
                super.save(entity);
            } else {
                // Se o animal já estiver registrado, lança a GenericException
                throw new GenericException("Este animal já está cadastrado para este proprietário.");
            }
        } catch (Exception e) {
            // Em caso de erro, imprime o erro e relança a exceção
            e.printStackTrace();
            throw new GenericException("Erro ao salvar o animal: " + e.getMessage());
        }
    }
}
