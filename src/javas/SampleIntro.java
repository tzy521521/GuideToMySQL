package javas;

import java.sql.*;

/**
 * @author lp
 */
public class SampleIntro {
    public static void main(String[] args) {
        try {
            //可能会出现类找不到异常;java6支持驱动程序的自动加载，因此不需要显示的加载他们。
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //connects to MySQL
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/mylibrary?useSSL=true","root","843460950");
            //creates Statement object
            Statement statement=connection.createStatement();
            String query="SELECT publID, publName FROM publishers ORDER BY publName";
            //
            ResultSet resultSet=statement.executeQuery(query);
            //结果集ResultSet维护一个表，该表的当前行可以获得。
            while (resultSet.next()){
                int id = resultSet.getInt("publID");
                String name = resultSet.getString("publName");
                System.out.println("ID: " + id + "  Name: " + name);
            }
            resultSet.close();
        }catch (Exception e){
            System.out.println("Error: " + e.toString() );
        }
    }
}