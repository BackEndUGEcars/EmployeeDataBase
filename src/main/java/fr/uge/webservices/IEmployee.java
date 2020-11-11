package fr.uge.webservices;

import org.json.simple.parser.ParseException;

import java.rmi.RemoteException;

public interface IEmployee {
    String getFirstName() throws RemoteException;
    String getLastName() throws RemoteException;

    String toJson(Long id) throws RemoteException;
    String getLogin() throws RemoteException;
    boolean isPasswordCorrect(String password) throws RemoteException;


}
