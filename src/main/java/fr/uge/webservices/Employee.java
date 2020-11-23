package fr.uge.webservices;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee extends UnicastRemoteObject implements IEmployee{
	private final String firstName;
    private final String lastName;
    private String login;
    private String password;
    private final List<Long> carRented = new ArrayList<>();



    public Employee(String firstName, String lastName) throws RemoteException {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, String login, String password) throws RemoteException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
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
                ", 'login':'" + login + '\'' +
                ", 'password':'" + password + '\'' +
                '}';
    }

    public static Employee createEmployee(String json) throws ParseException, RemoteException {
        JSONParser parser = new JSONParser();
        var jsonObject = (JSONObject) parser.parse(json);
        var first = (String) jsonObject.get("firstName");
        var last = (String) jsonObject.get("lastName");
        var login = (String) jsonObject.get("login");
        var password = (String) jsonObject.get("password");
        var employee = new Employee(first, last, login, password);


        return employee;
    }

    public String getLogin() throws RemoteException {
        return login;
    }


    public boolean isPasswordCorrect(String password) throws RemoteException{
        return password.equals(this.password);
    }

    public boolean addRent(Long idCar) throws RemoteException{
        if (carRented.contains(idCar)) return false;
        carRented.add(idCar);
        System.out.println("when rented " + carRented);
        return true;
    }

    public boolean removeRent(Long carId) throws RemoteException{
        return carRented.remove(carId);
    }

    public List<Long> getCarRented() throws RemoteException{
    	System.out.println("when getted " + carRented);
        return carRented;
    }
}
