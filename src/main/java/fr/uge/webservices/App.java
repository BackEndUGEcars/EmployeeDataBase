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
			System.out.println("RMI ok");
		} catch (Exception e) {
			System.err.println("Problem: " + e);
		}
    	/*
        var employeeBase = new EmployeeDataBase();
        employeeBase.init();

        System.out.println(employeeBase.toJson());*/
    }
}
