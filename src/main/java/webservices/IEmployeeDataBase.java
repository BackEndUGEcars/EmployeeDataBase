package webservices;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public interface IEmployeeDataBase {
    IEmployee getEmployee(Long id);
    boolean removeEmployee(Long id);
    boolean addEmployee(IEmployee t);
    Map<Long, IEmployee> getEmployeeMap();
    void init() throws IOException, ParseException;
    String toJson();
}
