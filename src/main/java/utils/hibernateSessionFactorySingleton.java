package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repositories.AdminRepository;
import repositories.UsuariosRepository;
import models.Admin;
import models.Usuarios;

public class hibernateSessionFactorySingleton {
    
    private static SessionFactory sessionFactory;
    private static AdminRepository adminRepository;
    private static UsuariosRepository usuariosRepository;

    static {
        try {
            // Cria a SessionFactory apenas uma vez
            sessionFactory = new Configuration().configure().buildSessionFactory();

            // Inicializa os repositórios
            adminRepository = new AdminRepository(sessionFactory);
            usuariosRepository = new UsuariosRepository(sessionFactory);

            // Inicializa o administrador padrão
            initializeAdmin();

        } catch (Throwable ex) {
            System.err.println("Falha na criação da SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static AdminRepository getAdminRepository() {
        return adminRepository;
    }

    public static UsuariosRepository getUsuariosRepository() {
        return usuariosRepository;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // Método para inicializar o administrador padrão
    private static void initializeAdmin() {
        try (var session = sessionFactory.openSession()) {
            // Verifica se o admin padrão já existe
            Admin admin = adminRepository.findByField("username", "admin");

            if (admin == null) {
                // Cria o administrador padrão com username "admin" e senha "admin"
                admin = new Admin("admin", "admin");
                adminRepository.save(admin);
                System.out.println("Administrador padrão criado com sucesso.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o administrador padrão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
