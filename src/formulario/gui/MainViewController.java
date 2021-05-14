/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.gui;

import formulario.Main;
import formulario.dao.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author zecaubun
 */
public class MainViewController implements Initializable {
    
    @FXML
    private MenuItem menuItemForm;
    @FXML
    private MenuItem menuItemShow;
    @FXML
    private MenuItem menuItemAbout;
    
    public void carregaTelaAbout(){
        carregaTela("/formulario/gui/About.fxml", x->{});
    }
    public void carregaTelaShow(){
        carregaTela("/formulario/gui/UsersView.fxml",(UsersViewController userC)->{
        
            userC.setUsuarioDao(new UsuarioDAO());
            userC.atualizaTabela();
        
        });
    }
    
    public void carregaTelaForm(){
        carregaTela("/formulario/gui/FormView.fxml", x-> {});
    }
    
    private synchronized <T> void carregaTela(String caminhoTela, Consumer<T> acaoIniciar){
           
        try{
            
            FXMLLoader carregadorTelaSec = new FXMLLoader(getClass().getResource(caminhoTela));
            VBox vbSecundario = carregadorTelaSec.load();
            Scene cenaPrincipal = Main.getCenaPrincipal();
            
            VBox vbPrincipal = (VBox)((ScrollPane)cenaPrincipal.getRoot()).getContent();
            Node menuPrincipal = vbPrincipal.getChildren().get(0);
            vbPrincipal.getChildren().clear();
            vbPrincipal.getChildren().add(menuPrincipal);
            vbPrincipal.getChildren().addAll(vbSecundario.getChildren());
            
            T respControlador = carregadorTelaSec.getController();
            acaoIniciar.accept(respControlador);
        
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
          
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
