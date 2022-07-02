package com.example.onetomany;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.spi.ServiceRegistry;
import java.util.Collection;

@SpringBootApplication
public class OneToManyApplication {

	public static void main(String[] args) {

		Laptop lap = new Laptop();
		lap.setLid(101);
		lap.setLname("Dell");

		Student st = new Student();
		st.setSid(1);
		st.setSname("A");
		st.setMarks(100);
		st.getLaptop().add(lap);
		lap.getStudent().add(st);
		Configuration con = new Configuration().configure().addAnnotatedClass(Laptop.class).addAnnotatedClass(Student.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		Query q1 = session.createQuery("from Student where sid=1");
		q1.setCacheable(true);
		session.getTransaction().commit();
	/*
		session.save(lap);
		session.save(st);
		Student a = session.get(Student.class,1);
		Collection<Laptop> l = a.getLaptop();
		for (Laptop l1:
			 l) {
			System.out.println(l1);
		}
	*/

		/*
		session s = sf.openSession();
		s.beginTransaction();
		Student a = (Student) s.get(Student.class,1);
		s.getTransaction().commit();
		s.close();
		session s1 = sf.openSession();
		s1.beginTransaction();
		Student a = (Student) s1.get(Student.class,1);
		s1.getTransaction().commit();
		s1.close();

		*/

	}

}
