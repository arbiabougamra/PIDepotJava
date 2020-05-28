/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import Services.IserviceStock;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import java.sql.Time;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.event.Event;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Etudiant
 */
public class StockInterfaceController implements Initializable, IserviceStock<ActionEvent> {

    @FXML
    private TableView<Produit> Table_stock;
    @FXML
    private TableColumn<?, ?> nom_col;
    @FXML
    private TableColumn<?, ?> categorie_col;
    @FXML
    private TableColumn<?, ?> marque_col;
    @FXML
    private TableColumn<?, ?> date1_col;
    @FXML
    private TableColumn<?, ?> date2_col;
    @FXML
    private TableColumn<?, ?> quantite_col;
    @FXML
    private TableColumn<?, ?> prix_col;

    private ObservableList<Produit> data;
    private PreparedStatement pst;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;
    private DataSource conn;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton Affecter_Mg;
    @FXML
    private JFXButton delete_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private ImageView ImageView;
    @FXML
    private JFXTextField tfsearchbyid;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private ImageView logo;
 
    @FXML
    private JFXButton stat;
    @FXML
    private Label lblTime;
    @FXML
    private JFXButton btnExport;
    private JFXButton admin;
    private int minute;
    private int hour;
    private int second;
    private String am_pm;

    private boolean Timer;
    private Integer tseconds;
    private Calendar cal;
    @FXML
    private JFXButton dash;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance();
        try {
            InitStock();
        } catch (SQLException ex) {
            Logger.getLogger(StockInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @FXML
    public void AddStock(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) add_btn.getScene().getWindow();
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/StockADDInterface.fxml")); //
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Stock ADD");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StockInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    @FXML
    public boolean DeleteStock(ActionEvent t) throws SQLException {
        conn = DataSource.getInstance();

        int selectedIndex = Table_stock.getSelectionModel().getSelectedIndex();
        Produit m = (Produit) Table_stock.getSelectionModel().getSelectedItem();
        int tempItemTag = m.getId();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getNom()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = DataSource.getInstance();
                String query1 = "DELETE FROM produit WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                Table_stock.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }

    @Override
    @FXML
    public boolean UpdateStock(ActionEvent t) throws SQLException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/StockUPDATE.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            
            Stage stage = new Stage();
            stage.setTitle("UPDATE");
            
            StockUPDATEController upc = fxmlLoader.getController();
            upc.TranslatingData(Table_stock.getSelectionModel().getSelectedItem());
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(StockInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }

    @Override
    public void InitStock() throws SQLException {
        conn = DataSource.getInstance();     
        try {
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = DataSource.getInstance();
               // data.addAll(data);
                data.add(new Produit(rs.getInt(1),rs.getString(3),rs.getInt(7),rs.getInt(2),(int) rs.getDouble(5),rs.getString(4), rs.getDate(9), rs.getDate(10)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        marque_col.setCellValueFactory(new PropertyValueFactory<>("marque"));
        categorie_col.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        date1_col.setCellValueFactory(new PropertyValueFactory<>("dates"));
        date2_col.setCellValueFactory(new PropertyValueFactory<>("datee"));
        quantite_col.setCellValueFactory(new PropertyValueFactory<>("quantite"));

    //   Table_stock.setItems(null);
       Table_stock.setItems(data);
    }

    @Override
    @FXML
    public void search(ActionEvent t) {
                ObservableList data = Table_stock.getItems();
        tfsearchbyid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                Table_stock.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Produit> subentries = FXCollections.observableArrayList();

            long count = Table_stock.getColumns().stream().count();
            for (int i = 0; i < Table_stock.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + Table_stock.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(Table_stock.getItems().get(i));
                        break;
                    }
                }
            }
            Table_stock.setItems(subentries);

        });
    }

    

    @FXML
    private void selectionne(MouseEvent event) throws SQLException, MalformedURLException {
        conn= DataSource.getInstance();
        int indexSelected = Table_stock.getSelectionModel().getSelectedIndex();
        Produit s = (Produit) Table_stock.getSelectionModel().getSelectedItem();
         conn= DataSource.getInstance();
        int id = s.getId();



        try {
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit where produit.id='"+id+"'";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                       conn= DataSource.getInstance();
                String lien = rs.getString("nomfile");
                System.out.print(lien);

                String lien2 = lien.replaceAll("//", "/");

                File file = new File(lien2);
                String localUrl = file.toURI().toURL().toString();
               
                Image image = new Image(localUrl);
                ImageView.setImage(image);

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/GUI/StockInterface.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }
    
    
    public boolean AffecterMg(ActionEvent t) throws SQLException {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/AffecterMgInterface.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Affecter Au Magasin");
            AffecterMgInterfaceController upc = fxmlLoader.getController();
            upc.TranslatingData(Table_stock.getSelectionModel().getSelectedItem());
           
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(AffecterMgInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }
    
    @FXML
    private void CloseStageAutomaticly(Event e) throws IOException{
    final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
    
     private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.millis(Calendar.getInstance().get(Calendar.MILLISECOND)), (ActionEvent event) -> {
            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);
            int minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR);
            String am_pm = (cal.get(Calendar.AM_PM) == 0) ? "AM" : "PM";
            lblTime.setText(String.format("   %02d : %02d : %02d %s", hour, minute, second, am_pm));
            if (Timer) {
                if (tseconds == 0) {    
                    Timer = false;
                    //timer.setText("Time Out");
                } else {
                    //timer.setText(tseconds.toString());
                    tseconds--;
                }
            }
        }), new KeyFrame(Duration.millis(Calendar.getInstance().get(Calendar.MILLISECOND))));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    
    @FXML
    private void ExportExcel(ActionEvent event) {
         try {
           
            conn = DataSource.getInstance();
            Statement stmt1 = conn.getCnx().createStatement();
            //Variable counter for keeping track of number of rows inserted.  
            int counter = 1;
            FileOutputStream fileOut = null;
           
            String sql = "Select * from produit";

            //Creation of New Work Book in Excel and sheet.  
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            //Creating Headings in Excel sheet.  
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("id");//Row Name1  
            rowhead.createCell((short) 1).setCellValue("nom");//Row Name2  
            rowhead.createCell((short) 2).setCellValue("marque");//Row Name3  
            rowhead.createCell((short) 3).setCellValue("quantité");//Row Name4
            rowhead.createCell((short) 4).setCellValue("date début");//Row Name5 
            rowhead.createCell((short) 5).setCellValue("date fin");//Row Name5 

            ResultSet rs = stmt1.executeQuery(sql);
            while (rs.next()) {
                //Insertion in corresponding row  
                HSSFRow row = sheet.createRow((int) counter);
                /* Activity, Username, TIME_OF_EVENT are row names  
          * corresponding to table  
          * in related Database. */
                CellStyle dateCellStyle = hwb.createCellStyle();
                CellStyle dateCellStyle1 = hwb.createCellStyle();
                CreationHelper createHelper = hwb.getCreationHelper();
                //Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
                dateCellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

                row.createCell((short) 0).setCellValue(rs.getInt("id"));
                row.createCell((short) 1).setCellValue(rs.getString("nom"));
                row.createCell((short) 2).setCellValue(rs.getString("marque"));
                row.createCell((short) 3).setCellValue(rs.getInt("quantite"));

//                row.createCell((short) 3).setCellStyle(dateCellStyle);
//                row.createCell((short) 3).setCellValue(rs.getDate("date"));
            
                Cell dateS = row.createCell((short) 4);
                dateS.setCellValue(rs.getDate("dates"));
                dateS.setCellStyle(dateCellStyle);


                Cell dateE = row.createCell((short) 5);
                dateE.setCellValue(rs.getDate("datee"));
                dateE.setCellStyle(dateCellStyle1);

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);

                counter++;
                try {
                    //For performing write to Excel file  
                    fileOut = new FileOutputStream("StockByRaslen.xls");
                    hwb.write(fileOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Close all the parameters once writing to excel is compelte.  
            fileOut.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("All courses Has Been Exported in Excel Sheet");
            alert.showAndWait();
            rs.close();
            stmt1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    @FXML
   public void Stat(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) stat.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/Stat.fxml")); //
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Statistiques");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StockInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    }
  


