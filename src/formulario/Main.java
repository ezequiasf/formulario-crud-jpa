/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author zecaubun
 */
public class Main extends Application {
    
    private static Scene cenaPrincipal;
    
    @Override
    public void start(Stage palco) throws Exception {
        
        FXMLLoader carregador = new FXMLLoader(getClass().getResource("/formulario/gui/MainView.fxml"));
        ScrollPane primeiroNodoScroll = carregador.load();
        
        primeiroNodoScroll.setFitToHeight(true);
        primeiroNodoScroll.setFitToWidth(true);
        
        cenaPrincipal = new Scene(primeiroNodoScroll);
        palco.setScene(cenaPrincipal);
        palco.setTitle("Form");
        palco.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Scene getCenaPrincipal(){
        return cenaPrincipal;
    }
    
}
