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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class satilanevlerController implements Initializable {
	
	String sql="";
	int idm=0;
	Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;
	ResultSet getirilen=null;
	
	 public satilanevlerController() throws SQLException {
			baglanti=VeritabaniUtil.Baglan();
		}
	
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton rdt_sat1;

    @FXML
    private RadioButton rdt_kira2;
    
    @FXML
    private TextField txt_satilanDeger;
    
    @FXML
    private TableView<evsatiskayit> tblsatilantab;
    @FXML
    private TableColumn<evsatiskayit, Integer> KayitEv;

    @FXML
    private TableColumn<evsatiskayit, String> KayitEvturu;

    @FXML
    private TableColumn<evsatiskayit, String> KayitEvtoplamalan;

    @FXML
    private TableColumn<evsatiskayit, Integer> KayitEvodasayisi;

    @FXML
    private TableColumn<evsatiskayit, String> KayitEvdurum;

    @FXML
    private TableColumn<evsatiskayit, String> KayitEvadres;

    @FXML
    private TableColumn<evsatiskayit, Integer> KayitTc;

    @FXML
    private TableColumn<evsatiskayit, String> KayitAd;

    @FXML
    private TableColumn<evsatiskayit, String> KayitSoyad;

    @FXML
    private TableColumn<evsatiskayit, Integer> KayitTel;

    @FXML
    private TableColumn<evsatiskayit, String> KayitMail;

    @FXML
    private TableColumn<evsatiskayit, String> KayitAdres;
    @FXML
    private TableColumn<evsatiskayit, String> KayitFiyat;
    @FXML
    private ToggleGroup toogglegroup2;
    
    
    @FXML
    private Button btn_satilanSil;

    @FXML
    private Button btn_satkirsorgu;

    @FXML
    private Button btn_yazdir;

    @FXML
    private Button btn_listanasayfa;

    @FXML
    private Button btn_listcikis;
    
    

    @FXML
    void click_btn_listanasayfa(ActionEvent event) {
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
    void click_btn_listcikis(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void click_btn_satilantab(ActionEvent event) {

    }

    @FXML
    void click_btn_satkirsorgu(ActionEvent event) {
    	
    	KayitEvdurumsorgu(tblsatilantab);
    }
    public void KayitEvdurumsorgu(TableView<evsatiskayit> tblsatilantab) {
    	RadioButton KayitEvdurum=(RadioButton) toogglegroup2.getSelectedToggle();
    
    	sql="select *  from satilanevler where KayitEvdurum=?";
    	ObservableList<evsatiskayit> evkayitdurum=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,KayitEvdurum.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitdurum.add(new evsatiskayit(getirilen.getInt("KayitEv"),getirilen.getString("KayitEvturu"),getirilen.getString("KayitEvtoplamalan"),getirilen.getString("KayitEvodasayisi"),getirilen.getString("KayitEvdurum"),getirilen.getString("KayitEvadres"),getirilen.getString("KayitTc"),getirilen.getString("KayitAd"),getirilen.getString("KayitSoyad"),getirilen.getString("KayitTel"),getirilen.getString("KayitMail"),getirilen.getString("KayitAdres"),getirilen.getString("KayitFiyat")));
			}	
			tblsatilantab.setItems(evkayitdurum);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    
    
    
    
    
    
    
    

    @FXML
    void click_btn_satilanSil(ActionEvent event) {

    	sql="delete from satilanevler where  KayitEv=?";
    	int selectedIndex = tblsatilantab.getSelectionModel().getSelectedIndex();
	    String selectedItem = txt_satilanDeger.getText().toString();
		Alert alert = new Alert(Alert.AlertType.WARNING, "Silmek için OK, iptal etmek için CANCEL butonuna basýnýz.", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("Silmek istediðinizden emin misiniz?");
        alert.setTitle("Uyarý");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
            	try {
            	sorguIfadesi = baglanti.prepareStatement(sql);              
            	sorguIfadesi.setString(1, selectedItem);
            	sorguIfadesi.execute();       
            	tblsatilantab.getItems().remove(selectedIndex);
     	      
            	} catch(Exception e) {
        			e.printStackTrace();
        		}
            
            }
          });
        evsatiskayitGetir(tblsatilantab);

    }
    
    
    
    public void evsatiskayitGetir(TableView<evsatiskayit> tblsatilantab) {
    	sql="select * from satilanevler";
    	ObservableList<evsatiskayit> evmuskayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evmuskayitListe.add(new evsatiskayit(getirilen.getInt("KayitEv"),getirilen.getString("KayitEvturu"),getirilen.getString("KayitEvtoplamalan"),getirilen.getString("KayitEvodasayisi"),getirilen.getString("KayitEvdurum"),getirilen.getString("KayitEvadres"),getirilen.getString("KayitTc"),getirilen.getString("KayitAd"),getirilen.getString("KayitSoyad"),getirilen.getString("KayitTel"),getirilen.getString("KayitMail"),getirilen.getString("KayitAdres"),getirilen.getString("KayitFiyat")));
			}		
			
			KayitEv.setCellValueFactory(new PropertyValueFactory<>("KayitEv"));
			KayitEvturu.setCellValueFactory(new PropertyValueFactory<>("KayitEvturu"));
			KayitEvtoplamalan.setCellValueFactory(new PropertyValueFactory<>("KayitEvtoplamalan"));
			KayitEvodasayisi.setCellValueFactory(new PropertyValueFactory<>("KayitEvodasayisi"));
			KayitEvdurum.setCellValueFactory(new PropertyValueFactory<>("KayitEvdurum"));
			KayitEvadres.setCellValueFactory(new PropertyValueFactory<>("KayitEvadres"));
			KayitTc.setCellValueFactory(new PropertyValueFactory<>("KayitTc"));
			KayitAd.setCellValueFactory(new PropertyValueFactory<>("KayitAd"));
			KayitSoyad.setCellValueFactory(new PropertyValueFactory<>("KayitSoyad"));
			KayitTel.setCellValueFactory(new PropertyValueFactory<>("KayitTel"));
			KayitMail.setCellValueFactory(new PropertyValueFactory<>("KayitMail"));
			KayitAdres.setCellValueFactory(new PropertyValueFactory<>("KayitAdres"));
			KayitFiyat.setCellValueFactory(new PropertyValueFactory<>("KayitFiyat"));
			
			
			tblsatilantab.setItems(evmuskayitListe);
			System.out.print(evmuskayitListe.toString());
			
		} catch (Exception e) {
				System.out.print("Hataaaaa");
		}
    }
    
    
    

    @FXML
    void initialize() {
        
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
	
		evsatiskayitGetir(tblsatilantab);
	}
}
