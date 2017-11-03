package javas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class SampleBlob {
    public static void main(String[] args) {
        try {
            //可能会出现类找不到异常;java6支持驱动程序的自动加载，因此不需要显示的加载他们。
            Class.forName("com.mysql.jdbc.Driver");

            //connects to MySQL
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/exceptions?useSSL=true","root","843460950");
            //creates Statement object
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO test_blob (a_blob) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement1=connection.prepareStatement("SELECT a_blob FROM test_blob WHERE id=?");
            PreparedStatement preparedStatement2=connection.prepareStatement("DELETE FROM test_blob WHERE id=?");

            File readfile=new File("test.jpg");
            FileInputStream fileInputStream=new FileInputStream(readfile);
            preparedStatement.setBinaryStream(1,fileInputStream,(int)readfile.length());
            preparedStatement.executeUpdate();
            fileInputStream.close();

            //在用insert插入数据后，想获得刚插入记录的ID
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id=0;
            if (resultSet!=null&&resultSet.next()){
                id=resultSet.getLong(1);
            }
            File writefile =new File("coppy-test.jpg");
            if (writefile.exists()){
                writefile.delete();
                writefile.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(writefile);
            preparedStatement1.setLong(1,id);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            resultSet1.next();
            InputStream is=resultSet1.getBinaryStream(1);
            final int BSIZE=2^15;
            int n;
            byte[] buffer=new byte[BSIZE];
            while ((n=is.read(buffer,0,BSIZE))>0){
                fileOutputStream.write(buffer,0,n);
            }
            is.close();
            fileOutputStream.close();
            resultSet1.close();

            preparedStatement2.setLong(1, 5);
            preparedStatement2.executeUpdate();
        }catch (Exception e){
            System.out.println("Error: " + e.toString() );
        }
    }
}
