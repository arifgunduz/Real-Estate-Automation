package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class anamenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_evekle;

    @FXML
    private Button btn_evsor;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_evsat;

    @FXML
    void action_btn_cikis(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void action_btn_evekle(ActionEvent event) {
    	try {
    		Stage stage2=new Stage();
    	
    		
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("evekleme.fxml"));
			Scene scene = new Scene(pane2,915,611);
			
			stage2.setTitle("UÐUR EMLAK-Ev Ekleme");
			stage2.getIcons().add(new Image("file:img/llogo.png"));
			stage2.setScene(scene);
			
			stage2.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	

    }

    @FXML
    void action_btn_evsat(ActionEvent event) {
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
    void action_btn_evsor(ActionEvent event) {
    	try {
    		Stage stage2=new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("evsorgulama.fxml"));
			Scene scene = new Scene(pane2,1390,591);
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
    void initialize() {
        assert btn_evekle != null : "fx:id=\"btn_evekle\" was not injected: check your FXML file 'anamenu.fxml'.";
        assert btn_evsor != null : "fx:id=\"btn_evsor\" was not injected: check your FXML file 'anamenu.fxml'.";
        assert btn_cikis != null : "fx:id=\"btn_cikis\" was not injected: check your FXML file 'anamenu.fxml'.";
        assert btn_evsat != null : "fx:id=\"btn_evsat\" was not injected: check your FXML file 'anamenu.fxml'.";

    }
}
