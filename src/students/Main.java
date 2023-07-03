package students;

import connection.dbConnection;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage screan;
// Create Connection instance ..    
    static dbConnection c = new dbConnection();
    static Connection  conn = c.connect()  ; 
    
    @Override
    public void start(Stage stage) throws Exception {
        screan = stage ;
// Specify the screen layout ..        
        Parent root = FXMLLoader.load(getClass().getResource("container.fxml"));
        Scene scene = new Scene( root , 1100.0 , 600.0 );
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }    
}
