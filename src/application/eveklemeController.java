package application;

import java.net.URL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import com.IsteMySql.Util.VeritabaniUtil;
public class eveklemeController implements Initializable{
	String sql="";
	int idm=0;
	Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;
	ResultSet getirilen=null;
	
	 public eveklemeController() throws SQLException {
			baglanti=VeritabaniUtil.Baglan();
		}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo_il;

    @FXML
    private ComboBox<String> combo_ilce;

    @FXML
    private TextArea txt_adres;

    @FXML
    private RadioButton radio_aktif;

    @FXML
    private RadioButton radio_pasif;

    @FXML
    private TextField txt_kat;

    @FXML
    private TextField txt_evalan;

    @FXML
    private TextField txt_evoda;

    @FXML
    private ComboBox<String> combo_evtur;

    @FXML
    private TextField txt_evyapim;

    @FXML
    private TextField txt_evyas;

    @FXML
    private RadioButton radio_satilik;

    @FXML
    private RadioButton radio_kiralik;
    @FXML
    private TextField txt_fiyat;

    @FXML
    private Button btn_evbil;

    @FXML
    private Button btn_evresim;

    @FXML
    private Button btn_anasayfa;
    @FXML
    private Button btn_evekleTemizle;
    @FXML
    private ToggleGroup togglegroup;
    @FXML
    private ToggleGroup togglegroup1;
    
    
    
    
    

    @FXML
    void click_btn_anasayfa(ActionEvent event) {
    	try {
    		Stage stage2=new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("anamenu.fxml"));
			Scene scene = new Scene(pane2,915,611);
			stage2.setTitle("UÐUR EMLAK-Anamenü");
			stage2.getIcons().add(new Image("file:img/llogo.png"));
			stage2.setScene(scene);
			
			stage2.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}


    }
   
    @FXML
    void click_btn_evbil(ActionEvent event) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Uður Emlak");
    	alert.setHeaderText("Yeni ev bilgileri kaydedildi.");
    	alert.setContentText("Sorgulama tablosundan yeni evi görebilirsiniz.");
    	alert.showAndWait();
    	
    	
    	
    	
    	
    	
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
    
    	
    	RadioButton evtercih=(RadioButton) togglegroup1.getSelectedToggle();
    	
    	
		sql="insert into evekleme(Evturu,Il,Ilce,Adres,OdaSayisi,Toplamevalan,Yapimtarihi,KatNo,EvYasi,EvDurum,EvTercihi,Fiyat) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        
    	try {
	    	sorguIfadesi=baglanti.prepareStatement(sql);
	    	
	    	sorguIfadesi.setString(1,combo_evtur.getSelectionModel().getSelectedItem().trim());
	    	sorguIfadesi.setString(2,combo_il.getSelectionModel().getSelectedItem().trim());
	    	sorguIfadesi.setString(3,combo_ilce.getSelectionModel().getSelectedItem().trim());
	    	sorguIfadesi.setString(4,txt_adres.getText().trim() );
	    	sorguIfadesi.setString(5,txt_evoda.getText().trim());
	    	sorguIfadesi.setString(6,txt_evalan.getText().trim());
	    	sorguIfadesi.setString(7,txt_evyapim.getText().trim());
	    	sorguIfadesi.setString(8,txt_kat.getText().trim());
	    	sorguIfadesi.setString(9,txt_evyas.getText().trim());
	    	sorguIfadesi.setString(10, evdurum.getText().trim());
	    	sorguIfadesi.setString(11, evtercih.getText().trim());
	    	sorguIfadesi.setString(12, txt_fiyat.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	    
			

    	} catch (Exception e) {
    		System.out.print(e.getMessage().toString());
    		
    		
    	}
    	
  
    }
    	
    
	
    	

    


    @FXML
    void click_btn_evekleTemizle(ActionEvent event) {
    	
    	RadioButton evtercih=(RadioButton) togglegroup1.getSelectedToggle();
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
    	txt_evoda.setText(null);
    	txt_kat.setText(null);
    	txt_adres.setText(null);
    	txt_evalan.setText(null);
    	txt_evyas.setText(null);
    	txt_fiyat.setText(null);
    	txt_evyapim.setText(null);
    	
    	combo_evtur.getSelectionModel().selectFirst(); 
    	combo_il.getSelectionModel().selectFirst(); 
    	combo_ilce.getSelectionModel().selectFirst();  
    	evtercih.setSelected(false);
    	evdurum.setSelected(false);
    	
    	
    	
    	
    	
    }

    @FXML
    void initialize() {
    	
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combo_evtur.setPromptText("Seçiniz");   
    	combo_evtur.getItems().addAll("Seçiniz","Villa","Yalý","Daire","Müstakil","Rezidans");
    	
        
    	combo_il.setPromptText("Seçiniz");   
    	combo_il.getItems().addAll("Seçiniz","Hatay","Mersin");
    	
    	combo_ilce.setPromptText("Seçiniz");   
    	combo_ilce.getItems().addAll("Seçiniz","Antakya","Samandað","Ýskenderun","Belen","------","Mezitli","ÇÝftlikköy","Erdemli","YeniÞehir");
	}
   

	
}
