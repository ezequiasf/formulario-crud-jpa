/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.gui;

import formulario.dao.UsuarioDAO;
import formulario.entidade.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zecaubun
 */
public class FormViewController implements Initializable {
    
    private static Usuario user;
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtEmail;
    
    public void onBtEnviarAction(){
        UsuarioDAO userDao = new UsuarioDAO();
        if(user == null)
            userDao.salvaUsuario(new Usuario(txtNome.getText(),txtSenha.getText(),txtEmail.getText()));
        else{
           user.setNome(txtNome.getText());
           user.setSenha(txtSenha.getText());
           user.setEmail(txtEmail.getText());
           userDao.salvaUsuario(user);
           user = null;
        }    
    }
    
    public void setUser(Usuario user){
        FormViewController.user = user;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    

    
}
