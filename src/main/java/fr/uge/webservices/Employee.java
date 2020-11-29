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

/**
 * Allow to manage Employee
 *
 */
public class Employee extends UnicastRemoteObject implements IEmployee{
	private final String firstName;
    private final String lastName;
    private String login;
    private String password;
    private final List<Long> carRented = new ArrayList<>();

    /**
     * Employee constructor
     * @param firstName, the employee first name
     * @param lastName, the employee last name
     * @throws RemoteException
     */
    public Employee(String firstName, String lastName) throws RemoteException {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Employee constructor
     * @param firstName, the employee first name
     * @param lastName, the employee last name
     * @param login, the employee login
     * @param password, the employee password
     * @throws RemoteException
     */
    public Employee(String firstName, String lastName, String login, String password) throws RemoteException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    /**
     * Get the employee first name
     * @return the employee first name
     */
    public String getFirstName() throws RemoteException {
        return firstName;
    }

    /**
     * Get the employee last name
     * @return the employee last name
     */
    public String getLastName() throws RemoteException {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * Convert employee to JSON
     * @param id, the id of the employee
     * @return the JSOn representation of the employee
     */
    public String toJson(Long id) throws RemoteException {
        return "{" +
                "'id':" + id +
                ", 'firstName':'" + firstName + '\'' +
                ", 'lastName':'" + lastName + '\'' +
                ", 'login':'" + login + '\'' +
                ", 'password':'" + password + '\'' +
                '}';
    }

    /**
     * Create an Employee from a JSON
     * @param json, the json representation of the employee
     * @return the Employee object representation of the employee
     */
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

    /**
     * Get the login of the employee
     * @return the Employee object representation of the employee
     */
    public String getLogin() throws RemoteException {
        return login;
    }

    /**
     * Check if the given password is correct 
     * @param password, the employee password
     * @return true if the password is correct, false either
     */
    public boolean isPasswordCorrect(String password) throws RemoteException{
        return password.equals(this.password);
    }

    /**
     * Add a rent to an the employee
     * @param idCar, the id of the car
     * @return true if the car can be rented, false either
     */
    public boolean addRent(Long idCar) throws RemoteException{
        if (carRented.contains(idCar)) return false;
        carRented.add(idCar);
        return true;
    }

    /**
     * Remove the rent from the employee
     * @param carId, the id of the car
     * @return true if the car can be removed, false either
     */
    public boolean removeRent(Long carId) throws RemoteException{
        return carRented.remove(carId);
    }

    /**
     * Get all the car rented by the employee
     * @return List<Long> which represents all cars rented by the employee
     */
    public List<Long> getCarRented() throws RemoteException{
    	System.out.println("when getted " + carRented);
        return carRented;
    }
}
