package lesson1.homework2;

import lesson1.homework2.models.Car;
import lesson1.homework2.models.Owner;
import lesson1.homework2.models.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory
                .openSession();

        session.beginTransaction();

        session.save(new Owner("Susanne", new Car("AUDI", Type.CABRIOLET, 200, 300000, 2012)));
        session.save(new Owner("Bill", new Car("BMW", Type.SEDAN, 300, 450000, 2020)));
        session.save(new Owner("Andy", new Car("Renault", Type.HATCHBACK, 500, 300000, 2015)));
        session.save(new Owner("Mark", new Car("Paganini", Type.CABRIOLET, 700, 1300000, 2022)));
        session.save(new Owner("Lily", new Car("Honda", Type.UNIVERSAL, 140, 20000, 2002)));
        session.getTransaction().commit();

        session.createQuery("select o from Owner o", Owner.class).getResultList().forEach(System.out::println);


        session.close();
        sessionFactory.close();


    }
}