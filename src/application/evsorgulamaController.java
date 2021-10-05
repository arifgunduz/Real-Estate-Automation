package application;

import java.net.URL;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;


import com.IsteMySql.Util.VeritabaniUtil;
public class evsorgulamaController implements Initializable{
	String sql="";
	
	int idm=0;
	Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;

	ResultSet getirilen=null;
	
	 public evsorgulamaController() throws SQLException {
			baglanti=VeritabaniUtil.Baglan();
		}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo_so_il;

    @FXML
    private ComboBox<String> combo_so_ilce;

    @FXML
    private Button btn_sorgula1;

    @FXML
    private RadioButton radio_aktif1;

    @FXML
    private ToggleGroup togglegroup;

    @FXML
    private RadioButton radio_pasif1;

    @FXML
    private Button btn_sorgula3;

    @FXML
    private TextField txt_sor_kat;

    @FXML
    private TextField txt_sor_oda;
    @FXML
    private TextField txt_silinendeger;
    @FXML
    private TextField txt_guncelle;

    @FXML
    private ComboBox<String> combo_sor_tur;

    @FXML
    private Button btn_sorgula2;

    @FXML
    private RadioButton radio_satilik;

    @FXML
    private ToggleGroup togglegroup1;

    @FXML
    private RadioButton radio_kiralik;

    @FXML
    private Button btn_sorgula4;

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
    private Button btn_evsat;

    @FXML
    private Button btn_anasayfadon;

    @FXML
    private Button btn_secsil;

    @FXML
    private Button btn_genelgoster;
    @FXML
    private Button btn_sectumsor;
    @FXML
    private Button btn_sortemizle;
    @FXML
    private Button btn_güncsecsil;

    @FXML
    void click_btn_anasayfadon(ActionEvent event) {
    	try {
    		Stage stage2=new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("anamenu.fxml"));
			Scene scene = new Scene(pane2,915,611);
			
			stage2.setScene(scene);
			stage2.setTitle("UÐUR EMLAK-Anamenü");
			stage2.getIcons().add(new Image("file:img/llogo.png"));
			stage2.show();
		
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void click_btn_evsat(ActionEvent event) {
    	try {
    		Stage stage1=new Stage();
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("musterisatis.fxml"));
			Scene scene = new Scene(pane1,1230,690);
			stage1.setScene(scene);
			stage1.setTitle("UÐUR EMLAK-Yeni Ev Satýþ");
			stage1.getIcons().add(new Image("file:img/llogo.png"));
			stage1.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void click_btn_genelgoster(ActionEvent event) {
evGetir(tblsorgu);
    }
    
    @FXML
    void click_btn_secsil(ActionEvent event) {
    	
    	
    	
    	sql="delete from evekleme where  Emlakno=?";
    	int selectedIndex = tblsorgu.getSelectionModel().getSelectedIndex();
	    String selectedItem = txt_silinendeger.getText().toString();
		Alert alert = new Alert(Alert.AlertType.WARNING, "Silmek için OK, iptal etmek için CANCEL butonuna basýnýz.", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("Silmek istediðinizden emin misiniz?");
        alert.setTitle("Uyarý");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
            	try {
            	sorguIfadesi = baglanti.prepareStatement(sql);              
            	sorguIfadesi.setString(1, selectedItem);
            	sorguIfadesi.execute();       
     	        tblsorgu.getItems().remove(selectedIndex);
     	      
            	} catch(Exception e) {
        			e.printStackTrace();
        		}
            
            }
          });
        evGetir(tblsorgu);

    }

    @FXML
    void click_btn_sorgula1(ActionEvent event) {
    	ilSorgu(tblsorgu);
    	ilceSorgu(tblsorgu);
    	
    }
    public void ilSorgu(TableView<ev> tblsorgu) {
    	sql="select *  from evekleme where Il=? ";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_so_il.getSelectionModel().getSelectedItem().toString());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    public void ilceSorgu(TableView<ev> tblsorgu) {
    	sql="select *  from evekleme where Ilce=?";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_so_ilce.getSelectionModel().getSelectedItem().toString());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    @FXML
    void click_btn_sorgula2(ActionEvent event) {
    	
    	
     	
     	
		 if(txt_sor_kat.getText().length()>0 && txt_sor_oda.getText().length()>0 && combo_sor_tur.getSelectionModel().toString()!="Seçiniz") {
			Sorgu1(tblsorgu);
	    }
    	else if(txt_sor_kat.getText().length()>0) {
    		katSorgu(tblsorgu);
    	}else if(txt_sor_oda.getText().length()>0) {
    		odasayisiSorgu(tblsorgu);
    	}else if(combo_sor_tur.getSelectionModel().toString()!="Seçiniz") {
    		evturuSorgu(tblsorgu);
		}
    	
    
    }

	
    
    
    public void Sorgu1(TableView<ev> tblsorgu) {
    	sql="select *  from evekleme where KatNo=? and Odasayisi=? and Evturu=?";
    			
    			
    	
    
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,txt_sor_kat.getText().trim());
			sorguIfadesi.setString(2,txt_sor_oda.getText().trim());
			sorguIfadesi.setString(3,combo_sor_tur.getSelectionModel().getSelectedItem().toString());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
   
    
    
    
    
   public void katSorgu(TableView<ev> tblsorgu) {
	   sql="select *  from evekleme where KatNo=? ";
   	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
   	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,txt_sor_kat.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    public void odasayisiSorgu(TableView<ev> tblsorgu) {
    	sql="select *  from evekleme where Odasayisi=? ";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,txt_sor_oda.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    
    public void evturuSorgu(TableView<ev> tblsorgu) {
    	sql="select *  from evekleme where Evturu=?";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_sor_tur.getSelectionModel().getSelectedItem().toString());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    

    @FXML
    void click_btn_sorgula3(ActionEvent event) {
    				/**/
    	evDurumSorgu(tblsorgu);
    	
    }
    public void evDurumSorgu(TableView<ev> tblsorgu) {
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
    
    	sql="select *  from evekleme where EvDurum=?";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,evdurum.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    @FXML
    void click_btn_sorgula4(ActionEvent event) {
    	
    	evtercih(tblsorgu);
    	
    }
    public void evtercih(TableView<ev> tblsorgu) {
    	RadioButton evtercih=(RadioButton) togglegroup1.getSelectedToggle();
    	
    	sql="select *  from evekleme where EvTercihi=?";
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,evtercih.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
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
    void click_btn_sectumsor(ActionEvent event) {


    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
        RadioButton evtercihi=(RadioButton) togglegroup1.getSelectedToggle();
        
        
    
      if( combo_so_il.getSelectionModel().toString()!="Seçiniz" && txt_sor_kat.getText().length()>0 && txt_sor_oda.getText().length()>0 ){
        	    Sorgu4(tblsorgu);
      }else if(combo_so_ilce.getSelectionModel().toString()!="Seçiniz" && txt_sor_kat.getText().length()>0 && txt_sor_oda.getText().length()>0 ) {
	       		Sorgu7(tblsorgu);
      }else if(combo_so_il.getSelectionModel().toString()!="Seçiniz" && txt_sor_kat.getText().length()>0 && txt_sor_oda.getText().length()>0 && combo_sor_tur.getSelectionModel().toString()!="Seçiniz" ) {
        	   Sorgu5(tblsorgu);
      }else if(combo_so_il.getSelectionModel().toString()!="Seçiniz"  && txt_sor_oda.getText().length()>0 && combo_sor_tur.getSelectionModel().toString()!="Seçiniz" ) {
    	       Sorgu6(tblsorgu);
      }else if(combo_so_ilce.getSelectionModel().toString()!="Seçiniz" && txt_sor_kat.getText().length()>0 && txt_sor_oda.getText().length()>0 && combo_sor_tur.getSelectionModel().toString()!="Seçiniz") {
               Sorgu8(tblsorgu);
      }else if(combo_so_ilce.getSelectionModel().toString()!="Seçiniz"  && txt_sor_oda.getText().length()>0 && combo_sor_tur.getSelectionModel().toString()!="Seçiniz"  ) {
    	       Sorgu9(tblsorgu);
    
    }else   if(evdurum.getText().length()>0 && evtercihi.getText().length()>0 && combo_so_il.getSelectionModel().toString()!="Seçiniz" ) {
		       Sorgu2(tblsorgu);
}else if(evdurum.getText().length()>0 && evtercihi.getText().length()>0 && combo_so_ilce.getSelectionModel().toString()!="Seçiniz"){
	          Sorgu3(tblsorgu);
}
    	
    
    	
    	
    	
    	
    }
    
    public void Sorgu2(TableView<ev> tblsorgu) {
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
        RadioButton evtercihi=(RadioButton) togglegroup1.getSelectedToggle();
    	sql="select *  from evekleme where Il=? and EvDurum=? and EvTercihi=?";
    			
    			
    	
    
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_so_il.getSelectionModel().getSelectedItem().toString());
			sorguIfadesi.setString(2,evdurum.getText().trim());
			sorguIfadesi.setString(3,evtercihi.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    public void Sorgu3(TableView<ev> tblsorgu) {
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
        RadioButton evtercihi=(RadioButton) togglegroup1.getSelectedToggle();
    	sql="select *  from evekleme where Ilce=? and EvDurum=? and EvTercihi=?";
    			
    			
    	
    
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_so_ilce.getSelectionModel().getSelectedItem().toString());
			sorguIfadesi.setString(2,evdurum.getText().trim());
			sorguIfadesi.setString(3,evtercihi.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
    public void Sorgu4(TableView<ev> tblsorgu) {
    	
    	sql="select *  from evekleme where Il=? and KatNo=? and Odasayisi=?";
    			
    			
    	
    
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_so_il.getSelectionModel().getSelectedItem().toString());
			sorguIfadesi.setString(2,txt_sor_kat.getText().trim());
			sorguIfadesi.setString(3,txt_sor_oda.getText().trim());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
public void Sorgu5(TableView<ev> tblsorgu) {
    	
    	sql="select *  from evekleme where Il=? and KatNo=? and Odasayisi=? and Evturu=? ";
    			
    			
    	
    
    	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1,combo_so_il.getSelectionModel().getSelectedItem().toString());
			sorguIfadesi.setString(2,txt_sor_kat.getText().trim());
			sorguIfadesi.setString(3,txt_sor_oda.getText().trim());
			sorguIfadesi.setString(4,combo_sor_tur.getSelectionModel().getSelectedItem().toString());
			
			ResultSet getirilen=sorguIfadesi.executeQuery();
			while(getirilen.next()) {
				evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
				}		
			tblsorgu.setItems(evkayitListe);
		
			
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
    }
public void Sorgu6(TableView<ev> tblsorgu) {
	
	sql="select *  from evekleme where Il=? and Odasayisi=? and Evturu=? ";
			
			
	

	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
	try {
		sorguIfadesi=baglanti.prepareStatement(sql);
		sorguIfadesi.setString(1,combo_so_il.getSelectionModel().getSelectedItem().toString());
		sorguIfadesi.setString(2,txt_sor_oda.getText().trim());
		sorguIfadesi.setString(3,combo_sor_tur.getSelectionModel().getSelectedItem().toString());
		
		ResultSet getirilen=sorguIfadesi.executeQuery();
		while(getirilen.next()) {
			evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
			}		
		tblsorgu.setItems(evkayitListe);
	
		
	} catch (Exception e) {
		System.out.print(e.getMessage().toString());
	}
}
public void Sorgu7(TableView<ev> tblsorgu) {
	
	sql="select *  from evekleme where Ilce=? and KatNo=? and Odasayisi=?";
	
	
	
    
	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
	try {
		sorguIfadesi=baglanti.prepareStatement(sql);
		sorguIfadesi.setString(1,combo_so_ilce.getSelectionModel().getSelectedItem().toString());
		sorguIfadesi.setString(2,txt_sor_kat.getText().trim());
		sorguIfadesi.setString(3,txt_sor_oda.getText().trim());
		
		ResultSet getirilen=sorguIfadesi.executeQuery();
		while(getirilen.next()) {
			evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
			}		
		tblsorgu.setItems(evkayitListe);
	
		
	} catch (Exception e) {
		System.out.print(e.getMessage().toString());
	}
}
public void Sorgu8(TableView<ev> tblsorgu) {
	
	sql="select *  from evekleme where Ilce=? and KatNo=? and Odasayisi=? and Evturu=? ";
			
			
	

	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
	try {
		sorguIfadesi=baglanti.prepareStatement(sql);
		sorguIfadesi.setString(1,combo_so_ilce.getSelectionModel().getSelectedItem().toString());
		sorguIfadesi.setString(2,txt_sor_kat.getText().trim());
		sorguIfadesi.setString(3,txt_sor_oda.getText().trim());
		sorguIfadesi.setString(4,combo_sor_tur.getSelectionModel().getSelectedItem().toString());
		
		ResultSet getirilen=sorguIfadesi.executeQuery();
		while(getirilen.next()) {
			evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
			}		
		tblsorgu.setItems(evkayitListe);
	
		
	} catch (Exception e) {
		System.out.print(e.getMessage().toString());
	}
}
    
public void Sorgu9(TableView<ev> tblsorgu) {
	
	sql="select *  from evekleme where Ilce=? and Odasayisi=? and Evturu=? ";
			
			
	

	ObservableList<ev> evkayitListe=FXCollections.observableArrayList();
	try {
		sorguIfadesi=baglanti.prepareStatement(sql);
		sorguIfadesi.setString(1,combo_so_ilce.getSelectionModel().getSelectedItem().toString());
		sorguIfadesi.setString(2,txt_sor_oda.getText().trim());
		sorguIfadesi.setString(3,combo_sor_tur.getSelectionModel().getSelectedItem().toString());
		
		ResultSet getirilen=sorguIfadesi.executeQuery();
		while(getirilen.next()) {
			evkayitListe.add(new ev(getirilen.getInt("Emlakno"),getirilen.getString("Evturu"),getirilen.getString("Il"),getirilen.getString("Ilce"),getirilen.getString("Adres"),getirilen.getString("Odasayisi"),getirilen.getString("Toplamevalan"),getirilen.getString("Yapimtarihi"),getirilen.getString("KatNo"),getirilen.getInt("EvYasi"),getirilen.getString("EvDurum"),getirilen.getString("EvTercihi"),getirilen.getString("Fiyat")));
			}		
		tblsorgu.setItems(evkayitListe);
	
		
	} catch (Exception e) {
		System.out.print(e.getMessage().toString());
	}
}
    
    
    
    
    
    @FXML
    void click_btn_sortemizle(ActionEvent event) {
    	RadioButton evtercih=(RadioButton) togglegroup1.getSelectedToggle();
    	RadioButton evdurum=(RadioButton) togglegroup.getSelectedToggle();
    	txt_sor_oda.clear();
    	txt_sor_kat.clear();
    	combo_sor_tur.getSelectionModel().selectFirst(); 
    	combo_so_il.getSelectionModel().selectFirst(); 
    	combo_so_ilce.getSelectionModel().selectFirst();  
    	evtercih.setSelected(false);
    	evdurum.setSelected(false);
    }

    @FXML
    void click_btn_güncsecsil(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("guncelleme.fxml"));
    		AnchorPane pane2=(AnchorPane) loader.load();
    		
    		
    		
    		Stage stage2=new Stage();
			
			Scene scene = new Scene(pane2,1310,630);
			
			stage2.setTitle("UÐUR EMLAK-Ev Güncelleme");
			stage2.getIcons().add(new Image("file:img/llogo.png"));
			
			stage2.setScene(scene);
			
			stage2.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	

    }
    
    @FXML
    void initialize() {
       
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		combo_sor_tur.setPromptText("Seçiniz");   
		combo_sor_tur.getItems().addAll("Seçiniz","Villa","Yalý","Daire","Müstakil","Yalý Daire","Rezidans");
    	
        
    	combo_so_il.setPromptText("Seçiniz");   
    	combo_so_il.getItems().addAll("Seçiniz","Hatay","Mersin");
    	
    	combo_so_ilce.setPromptText("Seçiniz");   
    	combo_so_ilce.getItems().addAll("Seçiniz","Antakya","Samandað","Ýskenderun","Belen","------","Mezitli","ÇÝftlikköy","Erdemli","YeniÞehir");
		evGetir(tblsorgu);
	}
}
