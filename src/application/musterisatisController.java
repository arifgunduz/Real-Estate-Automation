package application;

import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.IsteMySql.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class musterisatisController implements Initializable {
	
	String sql="";
	int idm=0;
	Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;
	ResultSet getirilen=null;
	
	 public musterisatisController() throws SQLException {
			baglanti=VeritabaniUtil.Baglan();
		}
	
	
	
	
	
	
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_musevno;

    @FXML
    private TextField txt_musevalan;

    @FXML
    private TextField txt_musoda;

    @FXML
    private TextField txt_musevdurum;

    @FXML
    private TextArea txt_musadres;

    @FXML
    private TextField txt_sattc;

    @FXML
    private TextField txt_satad;

    @FXML
    private TextField txt_satsoyad;

    @FXML
    private TextField txt_sattel;

    @FXML
    private TextField txt_satmail;

    @FXML
    private TextArea txt_satadres;
    @FXML
    private TextField txt_musfiyat;
    
    @FXML
    private TableView<ev> tblsorgu;

    @FXML
    private TableColumn<ev, Integer> Emlakno;
    @FXML
    private TableColumn<ev, String> Evturu;
    @FXML
    private TableColumn<ev, String> Il;
    @FXML
    private TableColumn<ev, String> Ilce;
    @FXML
    private TableColumn<ev, String> Adres;
    @FXML
    private TableColumn<ev, String>  Odasayisi;
    @FXML
    private TableColumn<ev, String>  Toplamevalan;
    
    @FXML
    private TableColumn<ev, String>  Yapimtarihi;
    
    @FXML
    private TableColumn<ev, Integer> KatNo;

    @FXML
    private TableColumn<ev, Integer> EvYasi;

    @FXML
    private TableColumn<ev, String> EvDurum;

    @FXML
    private TableColumn<ev, String> EvTercihi;

    @FXML
    private TableColumn<ev, String> Fiyat;
    @FXML
    private ComboBox<String> combo_satismus;

    @FXML
    private ComboBox<String> combo_musevTercihi;
    
    
    
    @FXML
    private Button btn_kontrolet;
    
    
    
    


    @FXML
    private Button btn_bilgilerikaydet;

    @FXML
    private Button btn_musanasayfa;
    
    @FXML
    private Button btn_mustemizle;


    
    
    
    
    
    
  
    @FXML
    void click_btn_kontrolet(ActionEvent event) {
    	try {
    		Stage stage2=new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("satilanevler.fxml"));
			Scene scene = new Scene(pane2,1235,690);
			stage2.setTitle("UÐUR EMLAK-Satýlan veya Kiralanan Evler");
			stage2.getIcons().add(new Image("file:img/llogo.png"));
			stage2.setScene(scene);
			stage2.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    @FXML
    void click_btn_bilgilerikaydet(ActionEvent event) {
    	
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Uður Emlak");
    	alert.setHeaderText("Yeni satýþ bilgileri kaydedildi.");
    	alert.setContentText("Sorgulama tablosundan yeni satýþ bilgilerini görebilirsiniz.");
    	alert.showAndWait();
    	
    	
    	
    	sql="insert into satilanevler(KayitEv,KayitEvturu,KayitEvtoplamalan,KayitEvodasayisi,KayitEvdurum,KayitEvadres,KayitTc,KayitAd,KayitSoyad,KayitTel,KayitMail,KayitAdres,KayitFiyat) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	try {
	    	sorguIfadesi=baglanti.prepareStatement(sql);
	    	
	    	
	    	sorguIfadesi.setString(1,txt_musevno.getText().trim());
	    	sorguIfadesi.setString(2,combo_satismus.getSelectionModel().getSelectedItem().trim());
	    	sorguIfadesi.setString(3,txt_musevalan.getText().trim() );
	    	sorguIfadesi.setString(4,txt_musoda.getText().trim());
	    	sorguIfadesi.setString(5,combo_musevTercihi.getSelectionModel().getSelectedItem().trim());
	    	sorguIfadesi.setString(6,txt_musadres.getText().trim());
	    	sorguIfadesi.setString(7,txt_sattc.getText().trim());
	    	sorguIfadesi.setString(8,txt_satad.getText().trim());
	    	sorguIfadesi.setString(9,txt_satsoyad.getText().trim());
	    	sorguIfadesi.setString(10,txt_sattel.getText().trim());
	    	sorguIfadesi.setString(11, txt_satmail.getText().trim());
	    	sorguIfadesi.setString(12, txt_satadres.getText().trim());
	    	sorguIfadesi.setString(13, txt_musfiyat.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	    
			

    	} catch (Exception e) {
    		System.out.print(e.getMessage().toString());
    		
    		
    	}
    	
    	
    	
    	

    }

    @FXML
    void click_btn_musanasayfa(ActionEvent event) {

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
    void click_btn_mustemizle(ActionEvent event) {
    	
    	txt_musevno.setText(null);
    	txt_musevalan.setText(null);
    	txt_musoda.setText(null);
    	txt_musadres.setText(null);
    	txt_sattc.setText(null);
    	txt_satad.setText(null);
    	txt_satsoyad.setText(null);
    	txt_sattel.setText(null);
    	txt_satmail.setText(null);
    	txt_satadres.setText(null);
    	txt_musfiyat.setText(null);
    	
    	combo_satismus.getSelectionModel().selectFirst(); 
    	combo_musevTercihi.getSelectionModel().selectFirst(); 
    	
    	

    }
    
    
    public void evGetir(TableView<ev> tblsorgu) {
    	sql="select * from evekleme";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
			}		
			
			Emlakno.setCellValueFactory(new PropertyValueFactory<>("Emlakno"));
			Evturu.setCellValueFactory(new PropertyValueFactory<>("Evturu"));
			Il.setCellValueFactory(new PropertyValueFactory<>("Il"));
			Ilce.setCellValueFactory(new PropertyValueFactory<>("Ilce"));
			Adres.setCellValueFactory(new PropertyValueFactory<>("Adres"));
			Odasayisi.setCellValueFactory(new PropertyValueFactory<>("Odasayisi"));
			Toplamevalan.setCellValueFactory(new PropertyValueFactory<>("Toplamevalan"));
			Yapimtarihi.setCellValueFactory(new PropertyValueFactory<>("Yapimtarihi"));
			KatNo.setCellValueFactory(new PropertyValueFactory<>("KatNo"));
			EvYasi.setCellValueFactory(new PropertyValueFactory<>("EvYasi"));
			EvDurum.setCellValueFactory(new PropertyValueFactory<>("EvDurum"));
			EvTercihi.setCellValueFactory(new PropertyValueFactory<>("EvTercihi"));
			Fiyat.setCellValueFactory(new PropertyValueFactory<>("Fiyat"));
			
			tblsorgu.setItems(evkayitListe);
			System.out.print(evkayitListe.toString());
			

			
		} catch (Exception e) {
				System.out.print("Hataaaaa");
		}

    }
    
    @FXML
    void tblsorgu_click2(MouseEvent event) {
    	
    	
    	ev kayitlar =new ev();
    	kayitlar=(ev) tblsorgu.getItems().get(tblsorgu.getSelectionModel().getSelectedIndex());
    	
    	txt_musevno.setText(Integer.toString(kayitlar.getEmlakno()));
    	combo_satismus.setPromptText(kayitlar.getEvturu());
    	txt_musevalan.setText(kayitlar.getToplamevalan());
    	txt_musfiyat.setText(kayitlar.getFiyat());
    	txt_musoda.setText(kayitlar.getOdasayisi());
    	combo_musevTercihi.setPromptText(kayitlar.getEvTercihi());
    	txt_musadres.setText(kayitlar.getAdres());
    	
    	
    	
    
    	
    	
    	
    	

    }
    
    
    
    
    
    
    @FXML
    void initialize() {
       

    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	evGetir(tblsorgu);
    	
    	combo_satismus.setPromptText("Seçiniz");   
    	combo_satismus.getItems().addAll("Seçiniz","Villa","Yalý","Daire","Müstakil","Yalý Daire","Rezidans");
    	
    	combo_musevTercihi.setPromptText("Seçiniz");   
    	combo_musevTercihi.getItems().addAll("Seçiniz","Satýlýk Ev","Kiralýk Ev");
    	
    	
	}
}
