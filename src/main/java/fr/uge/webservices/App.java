package fr.uge.webservices;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
    	try {
			LocateRegistry.createRegistry(7778);
			IEmployeeDataBase c = new EmployeeDataBase();
			c.init();
			Naming.bind("rmi://localhost:7778/EmployeeDataBase", c);
			System.out.println("EmployeeDataBase RMI loaded");
		} catch (Exception e) {
			System.err.println("Problem: " + e);
		}
    }
}
