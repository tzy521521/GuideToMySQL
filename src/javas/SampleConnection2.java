package javas;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SampleConnection2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //用DataSource(javax.sql库，只是提供了一个接口)类创建连接，
            MysqlDataSource dataSource= new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("mylibrary");
            Connection connection = dataSource.getConnection("root","843460950");

            Statement statement=connection.createStatement();
            String query="SELECT publID, publName FROM publishers ORDER BY publName";
            ResultSet resultSet=statement.executeQuery(query);
            //结果集ResultSet维护一个表，该表的当前行可以获得。
            while (resultSet.next()){
                int id = resultSet.getInt("publID");
                String name = resultSet.getString("publName");
                System.out.println("ID: " + id + "  Name: " + name);
            }
            resultSet.close();
        }catch (Exception e){

        }
    }
}
