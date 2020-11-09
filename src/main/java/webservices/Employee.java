package webservices;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Employee implements IEmployee{
    private final String firstName;
    private final String lastName;
    private String login;
    private String password;



    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public String toJson(Long id){
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

    public String getLogin() {
        return login;
    }


    public boolean isPasswordCorrect(String password){
        return password.equals(this.password);
    }
}
