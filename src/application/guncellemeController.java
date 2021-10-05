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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class guncellemeController  implements Initializable{
String sql="";
	
	int idm=0;
	Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;

	ResultSet getirilen=null;
	
	 public guncellemeController() throws SQLException {
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
    private ToggleGroup togglegroup;

    @FXML
    private RadioButton radio_pasif;

    @FXML
    private Label emlaklabel;
    @FXML
    private TextField txt_kat;

    @FXML
    private TextField txt_evalan;

    @FXML
    private TextField txt_evoda;
    @FXML
    private TextField txt_emlakno;
    @FXML
    private ComboBox<String> combo_evtur;

    @FXML
    private TextField txt_evyapim;

    @FXML
    private TextField txt_evyas;

    @FXML
    private RadioButton radio_satilik;

    @FXML
    private ToggleGroup togglegroup1;

    @FXML
    private RadioButton radio_kiralik;

    @FXML
    private TextField txt_fiyat;
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
    private TableColumn<ev, Integer>  Odasayisi;
    @FXML
    private TableColumn<ev, Integer>  Toplamevalan;
    
    @FXML
    private TableColumn<ev, Integer>  Yapimtarihi;
    
    @FXML
    private TableColumn<ev, Integer> KatNo;

    @FXML
    private TableColumn<ev, Integer> EvYasi;

    @FXML
    private TableColumn<ev, String> EvDurum;

    @FXML
    private TableColumn<ev, String> EvTercihi;

    @FXML
    private TableColumn<ev, Integer> Fiyat;

  
    @FXML
    private Button btn_evbil;

    @FXML
    private Button btn_anasayfa;
    @FXML
    private Button btn_geridon;
    @FXML
    private Button btn_gnctemizle;


    @FXML
    void click_btn_gnctemizle(ActionEvent event) {
    	RadioButton evtercih=(RadioButton) togglegroup1.getSelectedToggle();
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
    	txt_evoda.setText(null);
    	txt_kat.setText(null);
    	txt_adres.setText(null);
    	txt_evalan.setText(null);
    	txt_evyapim.setText(null);
    	txt_evyas.setText(null);
    	txt_fiyat.setText(null);
    	combo_evtur.getSelectionModel().selectFirst(); 
    	combo_il.getSelectionModel().selectFirst(); 
    	combo_ilce.getSelectionModel().selectFirst();  
    	evtercih.setSelected(false);
    	evdurum.setSelected(false);
    }
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
    void click_btn_geridon(ActionEvent event) {
    	try {
    		Stage stage2=new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("evsorgulama.fxml"));
			Scene scene = new Scene(pane2,1370,591);
			stage2.setTitle("UÐUR EMLAK-Ev Sorgulama");
			stage2.getIcons().add(new Image("file:img/llogo.png"));
			stage2.setScene(scene);
			stage2.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }
    @FXML
    void tblsorgu_click(MouseEvent event) {
    	/*
    	 RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
         RadioButton evtercihi=(RadioButton) togglegroup1.getSelectedToggle();*/
    	ev kayitlar =new ev();
    	kayitlar=(ev) tblsorgu.getItems().get(tblsorgu.getSelectionModel().getSelectedIndex());
    	
    	txt_emlakno.setText(Integer.toString(kayitlar.getEmlakno()));
    	
    	combo_il.setPromptText(kayitlar.getIl());
    	combo_ilce.setPromptText(kayitlar.getIlce());
    	txt_kat.setText(kayitlar.getKatNo());
    	txt_evoda.setText(kayitlar.getOdasayisi());
    	combo_evtur.setPromptText(kayitlar.getEvturu());
    	txt_adres.setText(kayitlar.getAdres());
    	txt_evalan.setText(kayitlar.getToplamevalan());
    	txt_evyapim.setText(kayitlar.getYapimtarihi());
    	txt_evyas.setText(Integer.toString(kayitlar.getEvYasi()));
    
    	txt_fiyat.setText(kayitlar.getFiyat());
    	/*evdurum.setToggleGroup(togglegroup);
    	evdurum.setUserData(kayitlar.getEvDurum());
    	evtercihi.setToggleGroup(togglegroup1);
    	evtercihi.setUserData(kayitlar.getEvTercihi());*/
    	
    	
    	
    	
    	
    	
    }
    @FXML
    void click_btn_evbil(ActionEvent event) {
    	
    	ilGncl(tblsorgu);
    	ilceGncl(tblsorgu);
    	katGncl(tblsorgu);
    	odasayisiGncl(tblsorgu);
    	evturuGncl(tblsorgu);
    	evDurumGncl(tblsorgu);
    	evtercihGncl(tblsorgu);
    	adresGncl(tblsorgu);
    	toplamevalanGncl(tblsorgu);
    	evyapimGncl(tblsorgu);
    	evyasGncl(tblsorgu);
    	fiyatGncl(tblsorgu);
    	
    evGetir(tblsorgu);
   
    }	
    
   
    
    	
/*sql="update evekleme set Il=? ,Ilce=?,KatNo=?,Odasayisi=?,Evturu=?,EvDurum=?,EvTercihi=?,Adres=?,Toplamevalan=?,Yapimtarihi=?,EvYasi=?,Fiyat=?  where Emlakno=?";
        RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
        RadioButton evtercihi=(RadioButton) togglegroup1.getSelectedToggle();
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
	   	 	sorguIfadesi.setString(1,combo_il.getSelectionModel().getSelectedItem().toString());
	 	 	sorguIfadesi.setString(2,combo_ilce.getSelectionModel().getSelectedItem().toString());
	 	 	sorguIfadesi.setString(3,txt_kat.getText().trim());
	 	 	sorguIfadesi.setString(4,txt_evoda.getText().trim());
	 	 	sorguIfadesi.setString(5,combo_evtur.getSelectionModel().getSelectedItem().toString());
	 	 	sorguIfadesi.setString(6,evdurum.getText().trim());
	 	 	sorguIfadesi.setString(7,evtercihi.getText().trim());
	 	 	sorguIfadesi.setString(8,txt_adres.getText().trim());
	 	 	sorguIfadesi.setString(9,txt_evalan.getText().trim());
	 	 	sorguIfadesi.setString(10,txt_evyapim.getText().trim());
	 	 	sorguIfadesi.setString(11,txt_evyas.getText().trim());
	 	 	sorguIfadesi.setString(12,txt_fiyat.getText().trim());
	 	 	
	 		sorguIfadesi.setString(13, txt_emlakno.getText().trim());
	    	
	    	
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
   	 }
   	 	
	   	}*/
   
    
   
    public void ilGncl(TableView<ev> tblsorgu) {
sql="update evekleme set Il=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
	   	 	sorguIfadesi.setString(1,combo_il.getSelectionModel().getSelectedItem().toString());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
	   	}
    }
    public void ilceGncl(TableView<ev> tblsorgu) {
    	
    	
sql="update evekleme set Ilce=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
	   	 	sorguIfadesi.setString(1,combo_ilce.getSelectionModel().getSelectedItem().toString());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
	   	}
    }
   
    public void katGncl(TableView<ev> tblsorgu) {
sql="update evekleme set KatNo=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
	   	 	sorguIfadesi.setString(1,txt_kat.getText().trim());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
	   	}
    }
    public void odasayisiGncl(TableView<ev> tblsorgu) {
sql="update evekleme set Odasayisi=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
	   	 	sorguIfadesi.setString(1,txt_evoda.getText().trim());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
	   	}
    }
    
    public void evturuGncl(TableView<ev> tblsorgu) {
sql="update evekleme set Evturu=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
   	 	sorguIfadesi.setString(1,combo_evtur.getSelectionModel().getSelectedItem().toString());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
	   	}
    }
    

    
    public void evDurumGncl(TableView<ev> tblsorgu) {
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
sql="update evekleme set EvDurum=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
   	 	sorguIfadesi.setString(1,evdurum.getText().trim());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
	   	}
    }
    
    public void evtercihGncl(TableView<ev> tblsorgu) {
    	RadioButton evtercih=(RadioButton) togglegroup1.getSelectedToggle();
    	
sql="update evekleme set EvTercihi=?  where Emlakno=?";
    	
   	 	try {
   	 		sorguIfadesi=baglanti.prepareStatement(sql);
   	 	sorguIfadesi.setString(1,evtercih.getText().trim());
	    	
	    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
	    	sorguIfadesi.executeUpdate();
	    	evGetir(tblsorgu);
	    	
	   	} 
   	 	catch (Exception e) {
   	 		System.out.print(e.getMessage().toString());
	   	
   	 	}
    }
    
    
    public void adresGncl(TableView<ev> tblsorgu) {
    	sql="update evekleme set Adres=?  where Emlakno=?";
    	    	
    	   	 	try {
    	   	 		sorguIfadesi=baglanti.prepareStatement(sql);
    	   	 	sorguIfadesi.setString(1,txt_adres.getText().toString());
    		    	
    		    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
    		    	sorguIfadesi.executeUpdate();
    		    	evGetir(tblsorgu);
    		    	
    		   	} 
    	   	 	catch (Exception e) {
    	   	 		System.out.print(e.getMessage().toString());
    		   	
    		   	}
    	    }
    
    
    public void toplamevalanGncl(TableView<ev> tblsorgu) {
    	sql="update evekleme set Toplamevalan=?  where Emlakno=?";
    	    	
    	   	 	try {
    	   	 		sorguIfadesi=baglanti.prepareStatement(sql);
    	   	 	sorguIfadesi.setString(1,txt_evalan.getText().trim());
    		    	
    		    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
    		    	sorguIfadesi.executeUpdate();
    		    	evGetir(tblsorgu);
    		    	
    		   	} 
    	   	 	catch (Exception e) {
    	   	 		System.out.print(e.getMessage().toString());
    		   	
    		   	}
    	    }
    
    
    public void evyapimGncl(TableView<ev> tblsorgu) {
    	sql="update evekleme set Yapimtarihi=?  where Emlakno=?";
    	    	
    	   	 	try {
    	   	 		sorguIfadesi=baglanti.prepareStatement(sql);
    	   	 	sorguIfadesi.setString(1,txt_evyapim.getText().trim());
    		    	
    		    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
    		    	sorguIfadesi.executeUpdate();
    		    	evGetir(tblsorgu);
    		    	
    		   	} 
    	   	 	catch (Exception e) {
    	   	 		System.out.print(e.getMessage().toString());
    		   	
    		   	}
    	    }
    public void evyasGncl(TableView<ev> tblsorgu) {
    	sql="update evekleme set EvYasi=?  where Emlakno=?";
    	    	
    	   	 	try {
    	   	 		sorguIfadesi=baglanti.prepareStatement(sql);
    	   	 	sorguIfadesi.setString(1,txt_evyas.getText().trim());
    		    	
    		    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
    		    	sorguIfadesi.executeUpdate();
    		    	evGetir(tblsorgu);
    		    	
    		   	} 
    	   	 	catch (Exception e) {
    	   	 		System.out.print(e.getMessage().toString());
    		   	
    		   	}
    	    }
    public void fiyatGncl(TableView<ev> tblsorgu) {
    	sql="update evekleme set Fiyat=?  where Emlakno=?";
    	    	
    	   	 	try {
    	   	 		sorguIfadesi=baglanti.prepareStatement(sql);
    	   	 	sorguIfadesi.setString(1,txt_fiyat.getText().trim());
    		    	
    		    	sorguIfadesi.setString(2,txt_emlakno.getText().trim());
    		    	sorguIfadesi.executeUpdate();
    		    	evGetir(tblsorgu);
    		    	
    		   	} 
    	   	 	catch (Exception e) {
    	   	 		System.out.print(e.getMessage().toString());
    		   	
    		   	}
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
    void initialize() {
    
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combo_evtur.setPromptText("Seçiniz");   
    	combo_evtur.getItems().addAll("Seçiniz","Villa","Yalý","Daire","Müstakil","Yalý Daire","Rezidans");
    	
        
    	combo_il.setPromptText("Seçiniz");   
    	combo_il.getItems().addAll("Seçiniz","Hatay","Mersin");
    	
    	combo_ilce.setPromptText("Seçiniz");   
    	combo_ilce.getItems().addAll("Seçiniz","Antakya","Samandað","Ýskenderun","Belen","------","Mezitli","ÇÝftlikköy","Erdemli","YeniÞehir");
		evGetir(tblsorgu);
	}
}
