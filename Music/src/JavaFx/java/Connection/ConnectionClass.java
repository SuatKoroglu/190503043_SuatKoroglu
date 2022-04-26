package Connection;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionClass {
    public Connection connection;
    public  Connection getConnection(){
        String dbname="mydatabase";
        String userName= "root";
        String password= "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }


}
