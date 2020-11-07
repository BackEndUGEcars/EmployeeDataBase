package webservices;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class EmployeeDataBase implements IEmployeeDataBase{
    private Map<Long, IEmployee> employeeMap = new HashMap<>(); //Long for id
    private long idMap = 0;

    public Map<Long, IEmployee> getEmployeeMap() {
        return Collections.unmodifiableMap(employeeMap);
    }

    public IEmployee getEmployee(Long id){
        return employeeMap.get(id);
    }

    public boolean removeEmployee(Long id){
        return  null != employeeMap.remove(id);
    }

    public boolean addEmployee(IEmployee t){
        idMap++;
        employeeMap.put(idMap, t);
        return true;
    }


    public void init() throws IOException, ParseException {
        var json = IOUtils.toString(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("test.json")), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();


        var jsonObject = (JSONObject) parser.parse(json);
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

    public String toJson(){
        var sj = new StringJoiner(", ");
        employeeMap.forEach((id, e) -> {
            sj.add(e.toJson(id));
        });
        return "{" +
                "    'employees': [" +
                sj.toString() +
                "],    'idMap' : "+ idMap +"}";
    }

}
