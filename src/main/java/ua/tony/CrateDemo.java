package ua.tony;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.tony.entity.Product;

public class CrateDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Product.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
					
			Product product = new Product("Red Jeans", "Jeans", 228, "XXXL", 132.99, "THE BEST FUCKING YEAH");
			
			session.beginTransaction();
			
			session.save(product);
			
			session.getTransaction().commit();
			System.out.println("Done");
			
		} finally{
			factory.close();
		}
	}

}
