package kata6.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kata6.model.Person;

public class DataBaseList {
    
    public static List<Person> read(String fileName) throws ClassNotFoundException, SQLException {
        List<Person> listPerson = new ArrayList<>();
        
        Class.forName("org.sqlite.JDBC");
        Connection connect = DriverManager.getConnection("jdbc:sqlite:" + fileName); 
        
        Statement st = connect.createStatement();
        
        String query = "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        while	(rs.next()) {
            Integer id = rs.getInt(1);	
            String name	= rs.getString(2);	
            Character gender = rs.getString(3).charAt(0);	
            String birthDate = rs.getString(4);	
            Float weight = rs.getFloat(5);	
            String mail = rs.getString(6);	
            listPerson.add(new Person(id, name, gender, birthDate, weight, mail));	
	}	
        
        rs.close();
        st.close();
        connect.close();
       
        return listPerson;
    }
}
