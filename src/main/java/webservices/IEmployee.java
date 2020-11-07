package webservices;

import org.json.simple.parser.ParseException;

public interface IEmployee {
    String getFirstName();
    String getLastName();

    String toJson(Long id);


}
