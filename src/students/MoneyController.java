package students;

import baseClasses.m_money;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static students.Main.conn;

public class MoneyController implements Initializable {

    @FXML
    private TableView<m_money> tab_money;

    @FXML
    private TableColumn<m_money, String> col_money;

    @FXML
    private TableColumn<m_money, String> col_name;

    @FXML
    private TableColumn<m_money, String> col_code;

    @FXML
    private JFXComboBox<String> combo;

    @FXML
    private JFXRadioButton r3;

    @FXML
    private JFXRadioButton r2;

    @FXML
    private JFXRadioButton r1;

    String choice = null;
    String selectedMonth = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fill_combobox();
        initial_column();
    }

    public void handleRadioSelected(ActionEvent event) throws SQLException {
        // show data when click radiobuttons 
        if (r1.isSelected()) {
            choice = "1st";
        } else if (r2.isSelected()) {
            choice = "2nd";
        } else if (r3.isSelected()) {
            choice = "3rd";
        }
        showData_for_first_display(choice);
    }

    private void fill_combobox() {
        combo.getItems().add("يناير");
        combo.getItems().add("قبراير");
        combo.getItems().add("مارس");
        combo.getItems().add("ابريل");
        combo.getItems().add("مايو");
        combo.getItems().add("يونيو");
        combo.getItems().add("يوليو");
        combo.getItems().add("اغسطس");
        combo.getItems().add("سبتمبر");
        combo.getItems().add("اكتوبر");
        combo.getItems().add("نوفمبر");
        combo.getItems().add("ديسمبر");
    }

    @FXML
    void getSelectedMonth(ActionEvent event) {
        switch (combo.getValue()) {
            case "يناير":
                selectedMonth = "1";
                break;
            case "قبراير":
                selectedMonth = "2";
                break;
            case "مارس":
                selectedMonth = "3";
                break;
            case "ابريل":
                selectedMonth = "4";
                break;
            case "مايو":
                selectedMonth = "5";
                break;
            case "يونيو":
                selectedMonth = "6";
                break;
            case "يوليو":
                selectedMonth = "7";
                break;
            case "اغسطس":
                selectedMonth = "8";
                break;
            case "سبتمبر":
                selectedMonth = "9";
                break;
            case "اكتوبر":
                selectedMonth = "10";
                break;
            case "نوفمبر":
                selectedMonth = "11";
                break;
            case "ديسمبر":
                selectedMonth = "12";
                break;
        }
    }

    private void initial_column() {
        col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_money.setCellValueFactory(new PropertyValueFactory<>("check"));
        editCols();
    }

    private void editCols() {
        col_code.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCode(e.getNewValue());
        });
        col_name.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });
        col_money.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoney(e.getNewValue());
        });
        tab_money.setEditable(true);

    }

    public void showData_for_first_display(String ch) {
        ObservableList<m_money> mon = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select code , name from student where class = '" + ch + "'");

            while (rs.next()) {
                mon.add(new m_money(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        tab_money.setItems(mon);
    }

    public void savePaymeents() throws SQLException {
        m_money mon = new m_money();
        List<List<String>> arrlist = new ArrayList<>();
        try {
            PreparedStatement ps;
            getAddedRecords() ;
//            ps = conn.prepareStatement("insert into money1st (code , name, pay_ind , month) values(?,?,?,?)");
//            ps = conn.prepareStatement("update money1st set (code , name, pay_ind , month) values(?,?,?,?)");
            for (int i = 0; i < tab_money.getItems().size(); i++) {
                mon = tab_money.getItems().get(i);
                arrlist.add(new ArrayList<>());
                arrlist.get(i).add(mon.getCode());
                arrlist.get(i).add(mon.getName());
                if (mon.getCheck().isSelected()) {
                    arrlist.get(i).add("1");
                } else {
                    arrlist.get(i).add("0");
                }
                arrlist.get(i).add(selectedMonth);
            }
            if (!selectedMonth.equals("null")) {
                for (int row = 0; row < arrlist.size(); row++) {
                    for (int col = 0; col < arrlist.get(row).size(); col++) {
                        ps = conn.prepareStatement("UPDATE money1st SET code ='" + arrlist.get(row).get(0)
                                + "'and name = '" + arrlist.get(row).get(1)
                                + "'and pay_ind = '" + arrlist.get(row).get(2)
                                + "'and month = '" + arrlist.get(row).get(3) + "'");
                        ps.executeUpdate();
                    }

                }
            } else {
                System.out.println("not saved");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    private void getAddedRecords(){
        PreparedStatement ps ;
        try{
        ResultSet rs = conn.createStatement().executeQuery("select code , name from student where name not in (select name from money1st)   ");
        ps = conn.prepareStatement("insert into money1st (code , name) values(?,?)");
        while (rs.next()) {
            ps.setString(1, rs.getString(1));
            ps.setString(2, rs.getString(2));
            System.out.print(rs.getString(1)  + "     "  + rs.getString(2) );
            ps.executeUpdate();
        }
        }catch(Exception ex){
            System.out.println(ex.getMessage() + "ex11");
                
        }
        }
    }

