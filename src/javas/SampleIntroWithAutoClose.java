package javas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SampleIntroWithAutoClose {
    public static void main(String[] args)throws Exception {
        String query="SELECT publID, publName FROM publishers ORDER BY publName";
        Class.forName("com.mysql.jdbc.Driver");
        try(
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/mylibrary?useSSL=true","root","843460950");
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query)
        ){
            while (resultSet.next()){
                int id = resultSet.getInt("publID");
                String name = resultSet.getString("publName");
                System.out.println("ID: " + id + "  Name: " + name);
            }
        }
    }
}
