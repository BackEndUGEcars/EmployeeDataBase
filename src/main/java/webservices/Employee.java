package webservices;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.rmi.RemoteException;

public class Employee implements IEmployee{
    private final String firstName;
    private final String lastName;
    private String login;
    private String password;



    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() throws RemoteException {
        return firstName;
    }

    public String getLastName() throws RemoteException {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String toJson(Long id) throws RemoteException {
        return "{" +
                "'id':" + id +
                ", 'firstName':'" + firstName + '\'' +
                ", 'lastName':'" + lastName + '\'' +

                '}';
    }

    public static Employee createEmployee(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        var jsonObject = (JSONObject) parser.parse(json);
        var first = (String) jsonObject.get("firstName");
        var  last = (String) jsonObject.get("lastName");
        var employee = new Employee(first, last);


        return employee;
    }

    public String getLogin() throws RemoteException {
        return login;
    }


    public boolean isPasswordCorrect(String password) throws RemoteException{
        return password.equals(this.password);
    }
}
