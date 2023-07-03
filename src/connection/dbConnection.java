package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
// SQLite Connection class ..
    private Connection con;
    
     public Connection connect () {
         String url  = "jdbc:sqlite:H:\\java\\Sqlite DB\\std.db" ;
    try{
         Class.forName("org.sqlite.JDBC") ;
         con = DriverManager.getConnection(url);
         System.out.println("Connection success");
         return con ;
     }catch(  SQLException | ClassNotFoundException x){
        System.out.println(  x.getMessage() ) ;
     }
    return null ;
     }
             
}
