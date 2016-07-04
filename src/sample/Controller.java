package sample;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {


    @FXML
    private TableView <BIConversion.User>labelTable;
    @FXML
    private TableColumn<Object, Object> stockNameColumn;
    @FXML
    private TextArea idArea;
    @FXML
    private TextField nameTextField;
    private ObservableList<BIConversion.User> data;

    DBConnect connect = new DBConnect();

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TableView<BIConversion.User> getLabelTable() {
        return labelTable;
    }



    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextArea getIdArea() {
        return idArea;
    }

    public void setIdArea(TextArea idArea) {
        this.idArea = idArea;
    }

    DBConnect mDBConnect = new DBConnect();

    public TableView getStockNameTable;

    public TableColumn<Object, Object> getNameColumn() {
        mDBConnect.run();
        stockNameColumn.setText(mDBConnect.getMaterial());

        return stockNameColumn;
    }



    public void run() {
        mDBConnect.run();
        idArea.setText(mDBConnect.getMaterial());
        stockNameColumn.equals(mDBConnect.getStockNames());
        getNameColumn();
        labelTable = getStockNameTable;
    }

    public void load(ActionEvent actionEvent) {
        run();
    }


//    public String getArrival() {
//        mDBConnect.run();
//        estArrivalTable.
//    }
}
