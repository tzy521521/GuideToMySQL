package javas;

import java.sql.*;

public class SamplePreparedStatement {
    public static void main(String[] args) {
        try {
            //可能会出现类找不到异常;java6支持驱动程序的自动加载，因此不需要显示的加载他们。
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //connects to MySQL
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/mylibrary?useSSL=true","root","843460950");
            //creates Statement object
            String update="INSERT INTO publishers (publName) VALUES (?)";
            PreparedStatement preparedStatement=connection.prepareStatement(update);
            //
            // insert new publisher
            preparedStatement.setString(1, "Ratschiller Tobias");
            preparedStatement.executeUpdate();
            System.out.println("11");
            preparedStatement.close();
        }catch (Exception e){
            System.out.println("Error: " + e.toString() );
        }
    }
}
