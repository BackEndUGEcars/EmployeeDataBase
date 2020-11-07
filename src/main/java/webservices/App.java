package webservices;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        var employeeBase = new EmployeeDataBase();
        employeeBase.init();

        System.out.println(employeeBase.toJson());
    }
}
