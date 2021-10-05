package application;

import java.net.URL;

import java.util.ResourceBundle;



import com.IsteMySql.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.sql.*;

public class girisController {
	
	  
    
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField kuladi;

    @FXML
    private TextField sifre;

    @FXML
    private Button btn_girisyap;

    @FXML
    private Label lbl_sonuc;
    
    Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;
	ResultSet getirilen=null;
	String sql;
  

    @FXML
    void click_btn_girisyap(ActionEvent event) {
    	
    	sql="select * from login where kuladi=? and sifre=?";
    	baglanti=VeritabaniUtil.Baglan();
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, kuladi.getText().trim());
    		sorguIfadesi.setString(2, sifre.getText().trim());
    		
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		
    		if(!getirilen.next()) {
    			
    			
    			Alert alert=new Alert(AlertType.ERROR);
    	    	alert.setTitle("Emlak OTOMASYON");
    	    	alert.setHeaderText("Hata Mesajý");
    	    	alert.setContentText("Kullanýcý adý veya Þifre hatalý");
    	    	alert.showAndWait();
    		}else {
    			getirilen.getString(1);
    			System.out.println("girID:"+getirilen.getString("girID"));
    			System.out.println("kuladi:"+getirilen.getString("kuladi"));
    			System.out.println("sifre:"+getirilen.getString("sifre"));
    			
    			try {
    	    		
    				FXMLLoader loader=new  FXMLLoader(getClass().getResource("anamenu.fxml"));
    				AnchorPane pane2=(AnchorPane) loader.load();
    				Scene scene = new Scene(pane2,900,600);
    			    
    				
    				Stage stage2=new Stage();
    				stage2.getIcons().add(new Image("file:img/llogo.png"));

    				stage2.setScene(scene);
    				stage2.setTitle("UÐUR EMLAK-Anamenü");
    				
    				stage2.show();
    				
    				((Node)(event.getSource())).getScene().getWindow().hide();
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    		
    		
    	} catch (Exception e){
			lbl_sonuc.setText(e.getMessage().toString());
    		
    	}
    	
    

    }

    @FXML
    void initialize() {
      

    }
}
