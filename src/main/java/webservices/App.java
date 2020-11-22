package webservices;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

public class App {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            IEmployeeDataBase e = new EmployeeDataBase();
            e.init();
            Naming.bind("rmi://localhost:1099/EmployeeDataBase",(Remote) e);
            System.out.println("RMI ok");
        } catch (Exception e) {
            System.err.println("Problem: " + e);
        }

    }
}
