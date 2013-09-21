package com.test.zoo;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class PopulateAnimalList {

	public static void populateList( List<Animals> animals){

		//Hibernate stuff
		Configuration config = new Configuration().configure();
		config.addAnnotatedClass(Animals.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();    
		SessionFactory sf = config.buildSessionFactory(serviceRegistry) ;	  
		Session session = sf.getCurrentSession();	      
		session.beginTransaction();
		//add animals from DB table to List 
		List<Animals> newanimals=new ArrayList<Animals>();
		newanimals=session.createQuery("from Animals").list();
		 session.getTransaction().commit();
		 animals.addAll(newanimals);
		 for(Animals a:newanimals){
			 System.out.println(a.getName());
		 }
		 
			
	}
	 public static void addToTable(List<Animals> animals){
			Configuration config = new Configuration().configure();
			config.addAnnotatedClass(Animals.class);
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();    
			SessionFactory sf = config.buildSessionFactory(serviceRegistry) ;	  
			Session session = sf.getCurrentSession();	      
			session.beginTransaction();
			//add animals to DB
			for(Animals animal:animals){
				session.save(animal);
			}
			session.getTransaction().commit();
			System.out.println("ggwp");
			

		 
	 }

}
