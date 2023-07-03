package students;

import baseClasses.student;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import static students.ContainerController.lbl_msg;
import static students.Main.conn;

public class STD_dataController implements Initializable {

    @FXML
    private JFXTextField txt_std;

    @FXML
    private TableView<student> tab_std;

    @FXML
    private TableColumn<student, String> col_name;

    @FXML
    private TableColumn<student, String> col_code;

    @FXML
    private JFXRadioButton r3;

    @FXML
    private ToggleGroup grp;

    @FXML
    private JFXRadioButton r2;

    @FXML
    private JFXRadioButton r1;

    String choice = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initial_column();
    }

    @FXML
    void Add_entry(ActionEvent event) throws SQLException {
        PreparedStatement ps;
        student s = new student();
// Insert data into DB ..
        try {
            ps = conn.prepareStatement("insert into student (name , class) values(?,?)");
            ps.setString(1, txt_std.getText());
// insert class indicator     

            ps.setString(2, choice);
            ps.executeUpdate();

            s.setName(txt_std.getText());
            tab_std.getItems().add(s);
            search("where class ='" + choice + "' ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void initial_column() {
        col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        editCols();
    }

    private void editCols() {
        col_code.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCode(e.getNewValue());
        });
        col_name.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });
        tab_std.setEditable(true);
    }

    public void conditionSearch() throws SQLException {
        String s = txt_std.getText();
        search(" where name like '" + s + '%' + "'and class = '" + choice + "'");
    }

    public void search(String s) throws SQLException {
// search based on name ..     
        ObservableList<student> std1 = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from student " + s);

            while (rs.next()) {
                std1.add(new student(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tab_std.setItems(std1);
    }

    public void deleteButton() {
        // get the selected Row ..
        ObservableList<student> stdSelected, allstd;
        try {
            allstd = tab_std.getItems();
            stdSelected = tab_std.getSelectionModel().getSelectedItems();
            int index = tab_std.getSelectionModel().getSelectedIndex();
            student st = allstd.get(index);
            String value = st.getName();
            // remove from tableview..    
            stdSelected.forEach(allstd::remove);
            // remove from DB ..    
            PreparedStatement ps = conn.prepareStatement("delete from student where name ='" + value + "'and class = '" + choice + "'");
            ps.executeUpdate();
            System.out.println("Deleting success ");
//            lbl_msg.setText(" Deleting success ");
//            lbl_msg.setStyle("-fx-background-color: blue;");
//            lbl_msg.setBackground(Color.green);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void handleRadioSelected(ActionEvent event) throws SQLException {
        // show data when click radiobuttons 
        if (r1.isSelected()) {
            choice = "1st";
        } else if (r2.isSelected()) {
            choice = "2nd";
        } else if (r3.isSelected()) {
            choice = "3rd";
        }
        search("where class = '" + choice + "' ");

    }
}
