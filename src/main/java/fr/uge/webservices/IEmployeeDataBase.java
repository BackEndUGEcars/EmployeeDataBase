package fr.uge.webservices;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IEmployeeDataBase extends Remote{
    IEmployee getEmployee(Long id) throws RemoteException;
    boolean removeEmployee(Long id) throws RemoteException;
    boolean addEmployee(IEmployee t) throws RemoteException;
    Map<Long, IEmployee> getEmployeeMap() throws RemoteException;
    long getIDofLogin(String login, String password) throws RemoteException;
    void init() throws IOException, ParseException, RemoteException;
    String toJson() throws RemoteException;
}
