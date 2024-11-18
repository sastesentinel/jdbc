import java.sql.*;

// Class sqldb is used to perform database operations conncet,-
// insert, update,delete,select and close operations through various methods.
public class sqldb {
    static Connection conn; //Connection Object
    static Statement st; //Statement Object
    // Connect method establish the connection to the database
    static void connect()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.jdbc.Driver";
       // String url = "jdbc:mariadb://localhost:3306";
       // String driver = "org.mariadb.jdbc.Driver";
        String dbName = "javatest";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver); //Register JDBC Driver
            //Class.forName(driver).newInstance(); //Register JDBC Driver
            conn = DriverManager.getConnection(url+dbName,userName,password); //create a connection object
            System.out.println("Connection Established");
            st = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Used to close the connection with database
    static void connclose()
    {
        try{
            conn.close();
        }
        catch(Exception e)
        {  e.printStackTrace();
        }
    }

    static int iud_data(String str) // Used for insert, update, delete query
    {   int r=0;
        try {
            r=st.executeUpdate(str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return r;
    }
    static ResultSet fetchdata(String str) throws SQLException // Used for select query
    {
        return st.executeQuery(str);
    }
}
