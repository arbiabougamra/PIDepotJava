/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Produit;
import Entity.Promotion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import org.omg.CORBA.portable.InputStream;
import java.io.FileInputStream;
import jxl.write.WritableImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javafx.scene.control.ComboBox;
import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.Label;
import jxl.format.*;


    
/**
 * FXML Controller class
 *
 * @author PCS
 */



public class MagasinInterfaceController implements Initializable {
    
    private DataSource conn;
    private PreparedStatement pst;
    @FXML
    private TableView<Produit> Table_stock;
    @FXML
    private TableView<Produit> Table_Mg;
    
    private ObservableList<Produit> data;
    private ResultSet rs;
    
    @FXML
    private ImageView image_v;
    
    private Stage stage;
    private Scene scene;
     @FXML
    private ComboBox<String> choice;
    @FXML
    private ComboBox<String> combobox_filter;
    private ObservableList<String> ob = FXCollections.observableArrayList("marque", "categorie", "all");
    
    @FXML
    private TableColumn<?, ?> nom_col;
    @FXML
    private TableColumn<?, ?> categorie_col;
    @FXML
    private TableColumn<?, ?> marque_col;
    @FXML
    private TableColumn<?, ?> quantitemg_col;
    @FXML
    private TableColumn<?, ?> nvprix_col;
   
    @FXML
    private TableColumn<?, ?> prixmg_col;
    @FXML
    private JFXTextField tfsearchbyid;
    
    @FXML
    private JFXButton ges_promo ;
     private Connection con = DataSource.getInstance().getCnx();
      
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DataSource.getInstance();
        try {
            InitMagasin();
        } catch (SQLException ex) {
            Logger.getLogger(MagasinInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combobox_filter.setItems(ob);
    }    
    
      @FXML
    private void selectionne(MouseEvent event) throws SQLException, MalformedURLException {
        conn= DataSource.getInstance();
        int indexSelected = Table_Mg.getSelectionModel().getSelectedIndex();
        Produit s = (Produit) Table_Mg.getSelectionModel().getSelectedItem();
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
              //  String localUrl = file.toURI().toURL().toString();
                Image image = new Image("http://localhost/ProjetFinal/web/Upload/"+rs.getString("nomfile"));
            //   Image image = new Image(localUrl);
                image_v.setImage(image);

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }
    public void InitMagasin() throws SQLException {
        conn = DataSource.getInstance();

        try {

            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM produit";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = DataSource.getInstance();
               // data.addAll(data);
       data.add(new Produit(rs.getInt(1),rs.getString(3),rs.getInt(6),rs.getDouble(12),rs.getString(4),rs.getInt(2),rs.getDouble(8)));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantitemg_col.setCellValueFactory(new PropertyValueFactory<>("nvquantite"));
        prixmg_col.setCellValueFactory(new PropertyValueFactory<>("prix_Mg"));
        marque_col.setCellValueFactory(new PropertyValueFactory<>("marque"));
        categorie_col.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        nvprix_col.setCellValueFactory(new PropertyValueFactory<>("nvprix"));
        Table_Mg.setItems(null);
        Table_Mg.setItems(data);
    }
  
    
    
 
    
    
    @FXML
    public boolean DeleteProduit(ActionEvent t) throws SQLException {
  conn = DataSource.getInstance();

        int selectedIndex = Table_Mg.getSelectionModel().getSelectedIndex();
        Produit m = (Produit) Table_Mg.getSelectionModel().getSelectedItem();
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
                Table_Mg.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }
   
    
     @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/GUI/MagasinInterface.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }
    
    
      @FXML
    public void search(ActionEvent t) {
       ObservableList data = Table_Mg.getItems();
        tfsearchbyid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                Table_Mg.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Produit> subentries = FXCollections.observableArrayList();

            long count = Table_Mg.getColumns().stream().count();
            for (int i = 0; i < Table_Mg.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + Table_Mg.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(Table_Mg.getItems().get(i));
                        break;
                    }
                }
            }
            Table_Mg.setItems(subentries);

        });
    }
  
     
   @FXML
   public boolean UpdateProduit(ActionEvent t) throws SQLException {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/UpdateProduitMg.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Produit Mg");
            UpdateProduitMgController upc = fxmlLoader.getController();
            upc.TranslatingData(Table_Mg.getSelectionModel().getSelectedItem());
           
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(MagasinInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }
    
   
   
  
    @FXML
    private void jexcel(ActionEvent event) throws SQLException, IOException, WriteException {
    
          

            Connection cn = DataSource.getInstance().getCnx();
            WritableWorkbook myFirstWbook = null;
            String requete = "SELECT * FROM produit";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);    
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableWorkbook workbook = Workbook.createWorkbook(baos);
            

            // * Create Font ***//
            WritableFont fontBlue = new WritableFont(WritableFont.TIMES, 10);
            fontBlue.setColour(Colour.BLUE);

            WritableFont fontRed = new WritableFont(WritableFont.TIMES, 10);
            fontRed.setColour(Colour.RED);
            
            WritableFont fontWhite = new WritableFont(WritableFont.TIMES, 10);
            fontRed.setColour(Colour.WHITE);

            // * Sheet 1 ***//
           workbook = Workbook.createWorkbook(new File("Liste des Produits.xls"));
            WritableSheet ws1 = workbook.createSheet("Liste Mg", 0);
             WritableCellFormat cellFormat3 = new WritableCellFormat();
             cellFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.WHITE);
            java.io.File imageFile = new java.io.File("C:\\Users\\PCS\\Desktop\\java image\\LOGO1.PNG");
            BufferedImage input = ImageIO.read(imageFile);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(input, "PNG", bao);
            ws1.addImage(new WritableImage(0,0,4,6,bao.toByteArray()));
             ws1.setColumnView(0, 10);
             ws1.addCell(new Label(0, 0,"", cellFormat3));
             ws1.setColumnView(0, 10);
             ws1.addCell(new Label(0, 1,"", cellFormat3));
              ws1.setColumnView(2, 10);
             ws1.addCell(new Label(0, 2,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(0, 3,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(0, 4,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(0, 5,"", cellFormat3));
             ws1.setColumnView(0, 10);
             ws1.addCell(new Label(1, 0,"", cellFormat3));
               ws1.setColumnView(0, 10);
             ws1.addCell(new Label(1, 1,"", cellFormat3));
              ws1.setColumnView(2, 10);
             ws1.addCell(new Label(1, 2,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(1, 3,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(1, 4,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(1, 5,"", cellFormat3));
             
                 ws1.setColumnView(0, 10);
             ws1.addCell(new Label(2, 0,"", cellFormat3));
               ws1.setColumnView(0, 10);
             ws1.addCell(new Label(2, 1,"", cellFormat3));
              ws1.setColumnView(2, 10);
             ws1.addCell(new Label(2, 2,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(2, 3,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(2, 4,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(2, 5,"", cellFormat3));
             
             
               ws1.setColumnView(0, 10);
             ws1.addCell(new Label(3, 0,"", cellFormat3));
               ws1.setColumnView(0, 10);
             ws1.addCell(new Label(3, 1,"", cellFormat3));
              ws1.setColumnView(2, 10);
             ws1.addCell(new Label(3, 2,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(3, 3,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(3, 4,"", cellFormat3));
             ws1.setColumnView(3, 10);
             ws1.addCell(new Label(3, 5,"", cellFormat3));
              
          
             	      
               ///
            
            // * Header ***//
            WritableCellFormat cellFormat1 = new WritableCellFormat(fontWhite);
            cellFormat1.setBackground(Colour.AQUA);
            cellFormat1.setAlignment(Alignment.CENTRE);
            cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLUE2);
             
            // * Data ***//
            WritableCellFormat cellFormat2 = new WritableCellFormat(fontBlue);
            
            
            // cellFormat2.setWrap(true);
            cellFormat2.setBackground(Colour.WHITE);
            cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat2.setWrap(true);
            cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLUE2);
            
            
            // * Header ***//
            ws1.setColumnView(0, 10);             
            ws1.addCell(new Label(0, 6, "Nom", cellFormat1));
            
            ws1.setColumnView(1, 15); 
            ws1.addCell(new Label(1, 6, "Prix", cellFormat1));
           
            ws1.setColumnView(2, 10);
            ws1.addCell(new Label(2, 6, "Marque", cellFormat1));
            
            ws1.setColumnView(3, 10); 
            ws1.addCell(new Label(3, 6, "Quantite", cellFormat1));
           
            int iRows =7;
             while((rs!=null) && (rs.next())) {
                ws1.addCell(new Label(0, iRows, rs.getString("Nom"), cellFormat2));
                ws1.addCell(new Label(1, iRows, rs.getString("Prix_Mg"), cellFormat2));
                ws1.addCell(new Label(2, iRows, rs.getString("Marque"), cellFormat2));
                ws1.addCell(new Label(3, iRows, rs.getString("nvquantite"), cellFormat2));
              
                ++iRows;
             }
            workbook.write();
            workbook.close();

            System.out.println("Excel file created.");

        }
   
    @FXML
    private void Imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
         printNode(Table_Mg);
    }
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout
        = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX= pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
    double scaleY= pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    node.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, node);
      if (success) {
        job.endJob();

      }
    }
    node.getTransforms().remove(scale);
  }
    
     public boolean AffecterPromo(ActionEvent t) throws SQLException {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/ADDPromotionInterface.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("ADD Promo");
            stage.setScene(new Scene(root));
            stage.show();
       
        } catch (IOException ex) {
            Logger.getLogger(AffecterMgInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            return true;
    }
       public ObservableList<Produit> afficherProduit() {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        ResultSet rs = null;
        
        try {
            pt = con.prepareStatement("select * from produit");
            rs = pt.executeQuery();
             Produit p = new Produit();
            while (rs.next()) {
                 p = new Produit(rs.getInt(1),rs.getString(3),rs.getDouble(12),rs.getString(4),rs.getInt(2),rs.getDouble(8),rs.getInt(6));
                 ob.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MagasinInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ob;
    }
     
     public ObservableList<Produit> afficherProduitParMarque(String Marque) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
         PreparedStatement pt;
        try {
            
            pt = con.prepareStatement("select * from Produit where marque = '" + Marque + "'");
             ResultSet rs = pt.executeQuery();
            Produit p = new Produit();
            while (rs.next()) {
                p = new Produit(rs.getInt(1),rs.getString(7),rs.getDouble(12),rs.getString(8),rs.getInt(2),rs.getDouble(5),rs.getInt(9));
                ob.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MagasinInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;

    }
      public ObservableList<Produit> afficherProduitParCategorie(String categorie) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        Produit p;
        try {
            pt = con.prepareStatement("select * from Produit where id_categorie = '" + categorie + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                p = new Produit(rs.getInt(1),rs.getString(3),rs.getDouble(12),rs.getString(4),rs.getInt(2),rs.getDouble(8),rs.getInt(6));
                ob.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MagasinInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;

    }
      
       @FXML
    private void combobox_filter(ActionEvent event) {
        ObservableList<String> ob = FXCollections.observableArrayList();

        if (combobox_filter.getValue().toString().equals("categorie")) {
            
            try {
                String sql = "select id from categorie";
                rs = conn.getCnx().createStatement().executeQuery(sql);
                
                while (rs.next()) {
                    ob.add(rs.getString(1));
                }
            } catch (Exception e) {
            }
        } else if (combobox_filter.getValue().toString().equals("marque")) {
            
            try {
                String sql = "select marque from produit";
                  rs = conn.getCnx().createStatement().executeQuery(sql);
               
                while (rs.next()) {
                    ob.add(rs.getString(1));
                }
            } catch (Exception e) {
            }
        } else {
            ob = null;
        }
        choice.setItems(ob);

    }
     @FXML
    private void choice(ActionEvent event) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        String s = "";
         MagasinInterfaceController mgc = new MagasinInterfaceController();
        s = choice.getValue();
        if (combobox_filter.getValue().toString().equals("marque")) {
            ob.clear();
            ob = mgc.afficherProduitParMarque(s);
            Table_Mg.getItems().clear();
            Table_Mg.setItems(ob);
        }
            else if (combobox_filter.getValue().toString().equals("categorie")) {
            ob.clear();
            ob = mgc.afficherProduitParCategorie(s);
            Table_Mg.getItems().clear();
            Table_Mg.setItems(ob);
                    
             }
        else {
            ob.clear();
            ob = mgc.afficherProduit();
            Table_Mg.getItems().clear();
            Table_Mg.setItems(ob);

        }
       

        }
    }
    
   
   
    
    

