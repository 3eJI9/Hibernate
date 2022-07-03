package lesson1.homework1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Word.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory
                .openSession();

        session.beginTransaction();


        session.save(new Word("Leyla"));
        session.save(new Word("Zara"));
        session.save(new Word("Ameba"));
        session.save(new Word("Penelope"));
        session.save(new Word("Nina"));
        session.save(new Word("Susanne"));


        session.getTransaction().commit();

        List<Word> words = session.createQuery("select w from Word w", Word.class).getResultList();
        System.out.println(words);

        session.close();
        sessionFactory.close();


    }
}