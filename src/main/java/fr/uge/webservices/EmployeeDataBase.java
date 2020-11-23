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

public class EmployeeDataBase extends UnicastRemoteObject implements IEmployeeDataBase{
	
	private Map<Long, IEmployee> employeeMap = new HashMap<>(); //Long for id
    private long idMap = 0;

    public EmployeeDataBase() throws RemoteException {
        super();
    }

    public Map<Long, IEmployee> getEmployeeMap() throws RemoteException {
        return Collections.unmodifiableMap(employeeMap);
    }

    public IEmployee getEmployee(Long id) throws RemoteException{
        return employeeMap.get(id);
    }

    public boolean removeEmployee(Long id) throws RemoteException {
        return  null != employeeMap.remove(id);
    }

    public boolean addEmployee(IEmployee t) throws RemoteException{
        idMap++;
        employeeMap.put(idMap, t);
        return true;
    }


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

    public long getIDofLogin(String login, String password) throws RemoteException {

        for (Map.Entry<Long, IEmployee> entry : employeeMap.entrySet()) {
            if (login.equals(entry.getValue().getLogin()) && entry.getValue().isPasswordCorrect(password)){
                return entry.getKey();
            }
        }
        return -1L;
    }

}
