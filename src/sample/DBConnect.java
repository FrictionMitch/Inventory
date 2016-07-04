package sample;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

/**
 *
 * @author uMac
 */
public class DBConnect {

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Stock","mike","mike");
    }

    private String mMaterial;
    private String mEstArrival;
    private ResultSet mResultSet;
//    private List<String> mMaterialList = new ArrayList<String>();
    private ObservableList<BIConversion.User> data;

    public String getEstArrival() {
        return mEstArrival;
    }

    public String getMaterial() {
        return mMaterial;
    }


    public void run() {

        Controller controller = new Controller();
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
        // TODO code application logic here
        try {
            String host = "jdbc:derby://localhost:1527/Stock";
            String uName = "mike";
            String uPass = "mike";

            Connection connection = DriverManager.getConnection(host, uName, uPass);
            Statement statement = connection.createStatement();
            data = FXCollections.observableArrayList();

            String SQL = "SELECT * FROM LABELS";
            mResultSet = statement.executeQuery(SQL);

            for(int i=0 ; i<mResultSet.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                String k = String.valueOf(j);
                TableColumn col = new TableColumn(mResultSet.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue(j).toString());
                    }
                });

                controller.getLabelTable().getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            while (mResultSet.next()) {
                    //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=mResultSet.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(mResultSet.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);


            }

                //FINALLY ADDED TO TableView
                controller.getLabelTable().setItems(data);


                //OLD CODE
//                int id = results.getInt("ID");
//                mMaterial = results.getString("MATERIAL");
//                Date date = results.getDate("DATE_ORDERED");
//                int ordered = results.getInt("ORDERED_QTY");
//                int backOrdered = results.getInt("BACK_ORDER");
//                int floor = results.getInt("ON_FLOOR");
//                int size = results.getInt("SIZE");
//                mEstArrival = results.getString("ARRIVAL");





//                System.out.printf("%n%nMaterial ID: %s", id);
//                System.out.printf("%nMaterial Size: %s", size);
//                System.out.printf("%nDate Ordered: %s", date);
//                System.out.printf("%nBack Ordered: %s", backOrdered);
//                System.out.printf("%nMaterial On Floor: %s", floor);
//                Connection connect = new Connection();
//                Statement st = connect.Connect();



        }
        catch (SQLException sex) {
            JOptionPane.showMessageDialog(null, sex);
        }
    }

    public List<String> getStockNames() {

        return Arrays.asList(mMaterial);
    }

}
