package kosa.team3.gcs.service.service1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Service1Controller implements Initializable {

    //Field
    @FXML private WebView Service1webView;
    private WebEngine webEngine;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	initWebView();
    }
    
    public void initWebView() {
    	webEngine = Service1webView.getEngine();
    	webEngine.load("http://localhost:8080/FinalWebProject/delivery");
    }
   

}
