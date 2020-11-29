package fr.uge.webservices;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.*;

/**
 * Allow to manage Employee
 *
 */
public class EmployeeDataBase extends UnicastRemoteObject implements IEmployeeDataBase{
	
	private Map<Long, IEmployee> employeeMap = new HashMap<>(); //Long for id
    private long idMap = 0;

    /**
     * EmployeeDataBase constructor
     * @throws RemoteException
     */
    public EmployeeDataBase() throws RemoteException {
        super();
    }

    /**
     * Get the employee map
     * @return map representation if the employee data base with id as key 
     * and an IEmployee Object as value
     */
    public Map<Long, IEmployee> getEmployeeMap() throws RemoteException {
        return Collections.unmodifiableMap(employeeMap);
    }

    /**
     * Get an employee form the database
     * @param id, the employee id
     * @return an IEmployee object representation of the employee
     */
    public IEmployee getEmployee(Long id) throws RemoteException{
        return employeeMap.get(id);
    }

    /**
     * Remove an employee form the database
     * @param id, the employee id
     * @return true if employee can be removed, false either
     */
    public boolean removeEmployee(Long id) throws RemoteException {
        return  null != employeeMap.remove(id);
    }

    /**
     * Add an employee form the database
     * @param t, the employee 
     * @return true if employee can be added, false either
     */
    public boolean addEmployee(IEmployee t) throws RemoteException{
        idMap++;
        employeeMap.put(idMap, t);
        return true;
    }

    /**
     * Initialize the database
     */
    public void init() throws IOException, ParseException, org.json.simple.parser.ParseException, RemoteException {
        var json = IOUtils.toString(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("test.json")), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();


        JSONObject jsonObject = (JSONObject) parser.parse(json);
        var employees = (JSONArray) jsonObject.get("employees");
        var iterator = employees.iterator();
        while (iterator.hasNext()) {
            var s = iterator.next().toString();
            var jo = (JSONObject) parser.parse(s);
            var employee = Employee.createEmployee(s);
            employeeMap.put((Long) jo.get("id"), employee);
        }
        idMap = (long) jsonObject.get("idMap");
    }

    /**
     * Convert the database to a JSON representation
     * @return String the JSON database representation
     */
    public String toJson() throws RemoteException{
        var sj = new StringJoiner(", ");

        for (Map.Entry<Long, IEmployee> entry : employeeMap.entrySet()) {
            sj.add(entry.getValue().toJson(entry.getKey()));
        }
        return "{" +
                "    'employees': [" +
                sj.toString() +
                "],    'idMap' : "+ idMap +"}";
    }

    /**
     * Get the id of the specified login/passord
     * @return long the id of the employee which has this login/password
     */
    public long getIDofLogin(String login, String password) throws RemoteException {

        for (Map.Entry<Long, IEmployee> entry : employeeMap.entrySet()) {
            if (login.equals(entry.getValue().getLogin()) && entry.getValue().isPasswordCorrect(password)){
                return entry.getKey();
            }
        }
        return -1L;
    }

}
