package students;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

public class ContainerController implements Initializable {

    @FXML
    private BorderPane p1;

    @FXML
    private JFXButton std;

    @FXML
    private JFXButton money;

    @FXML
    static Label lbl_msg;

// For students buttom ..
// load students screen     
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("Students");
        Parent root = FXMLLoader.load(getClass().getResource("STD_data.fxml"));
        p1.setRight(root);
    }
// For money buttom ...
// load money screen     

    @FXML
    void handleButtonAction2(ActionEvent event) throws IOException {
        System.out.println("Money");
        Parent root = FXMLLoader.load(getClass().getResource("money.fxml"));
        p1.setRight(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
