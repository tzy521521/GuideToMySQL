package javas;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SampleIntroWithAutoClose {
    public static void main(String[] args)throws Exception {
        String query="SELECT publID, publName FROM publishers ORDER BY publName";
        Class.forName("com.mysql.jdbc.Driver");
        File file=new File("./publishers.txt");
        try(
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/mylibrary?useSSL=true","root","843460950");
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                PrintWriter printWriter=new PrintWriter(file)
        ){
            while (resultSet.next()){
                int id = resultSet.getInt("publID");
                String name = resultSet.getString("publName");
                printWriter.print(id+" ");
                printWriter.println(name);
                System.out.println("ID: " + id + "  Name: " + name);
            }
        }
    }
}
