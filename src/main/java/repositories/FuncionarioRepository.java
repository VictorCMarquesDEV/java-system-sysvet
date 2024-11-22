package repositories;

import org.hibernate.SessionFactory;

import exceptions.GenericException;
import models.Funcionario;
import models.Usuarios;

public class FuncionarioRepository extends GenericRepository<Funcionario, Long> {
    private UsuariosRepository usuariosRepository;

    public FuncionarioRepository(SessionFactory sessionFactory) {
        super(Funcionario.class, sessionFactory);
        this.usuariosRepository = new UsuariosRepository(sessionFactory);
    }

    @Override
    public void save(Funcionario entity) throws GenericException {
        super.save(entity);
        try {
            Usuarios usuario = new Usuarios(entity.getCpf(), entity.getCpf());
            usuariosRepository.save(usuario);
        } catch (GenericException e) {
            throw new GenericException("Erro ao criar usuário correspondente: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        Funcionario funcionario = findById(id);
        if (funcionario != null) {
            super.delete(id);
            Usuarios usuario = usuariosRepository.findByField("username", funcionario.getCpf());
            if (usuario != null) {
                usuariosRepository.delete(usuario.getId());
            }
        }
    }
}
