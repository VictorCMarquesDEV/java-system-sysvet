package utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateSessionFactorySingleton {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Cria a SessionFactory uma vez, quando a classe é carregada
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}