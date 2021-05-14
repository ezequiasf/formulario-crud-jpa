/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.gui;

import formulario.Main;
import formulario.dao.UsuarioDAO;
import formulario.entidade.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zecaubun
 */
public class UsersViewController implements Initializable {
    
    private UsuarioDAO userDao;
     
    @FXML
    private TableColumn<Usuario,Usuario> tbcEdit;
    @FXML
    private TableColumn<Usuario,Usuario> tbcRemove;
    @FXML
    private TableView<Usuario> tabelaUser;
    @FXML
    private Button btNewUser;
    @FXML
    private TableColumn<Usuario,Integer> tbcId;
    @FXML
    private TableColumn<Usuario,String> tbcUser;
    @FXML
    private TableColumn<Usuario,String> tbcEmail;
    
    private ObservableList<Usuario> obsList;
    
    public void onBtNewUserAction(){
       MainViewController mvc = new MainViewController();
       mvc.carregaTelaForm();
    }
    
    public void onBtEdit(Usuario user){
      MainViewController mvc = new MainViewController();
      mvc.carregaTelaForm();
      FormViewController fvc = new FormViewController();
      fvc.setUser(user);
    }
    
    public void onBtRemove(Usuario user){
        userDao.removeUsuario(user);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaNodos();
    }

    private void inicializaNodos(){
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcUser.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        
        Stage palco = (Stage)Main.getCenaPrincipal().getWindow();
        tabelaUser.prefHeightProperty().bind(palco.heightProperty());
    }
    
    public void setUsuarioDao(UsuarioDAO userDao){
        this.userDao = userDao;
    }
    
    public void atualizaTabela(){
        if(userDao == null)
            throw new IllegalStateException("O Objeto de acesso ao DB não foi inicializado.");
        List<Usuario> lista = userDao.encontraTodosUsuarios();
        obsList = FXCollections.observableArrayList(lista);
        tabelaUser.setItems(obsList);
        iniciarBtnEdit();
        iniciarBtnRemove();
    }
    
    private void iniciarBtnEdit(){
        tbcEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tbcEdit.setCellFactory(param -> new TableCell<Usuario,Usuario>(){
            
            private final Button btnEdit = new Button("Editar");
            
            @Override
            protected void updateItem(Usuario obj, boolean empty){
                super.updateItem(obj, empty);
                if(obj == null){
                    setGraphic(null);
                    return; 
                }
                setGraphic(btnEdit);
                btnEdit.setOnAction(x-> onBtEdit(obj));
            }
        });
    }
    
    private void iniciarBtnRemove(){
        tbcRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tbcRemove.setCellFactory(param -> new TableCell<Usuario,Usuario>(){
            
            private final Button btnRemove = new Button("Deletar");
            
            @Override
            protected void updateItem(Usuario obj, boolean empty){
                super.updateItem(obj, empty);
                if(obj == null){
                    setGraphic(null);
                    return; 
                }
                setGraphic(btnRemove);
                btnRemove.setOnAction(x-> onBtRemove(obj));
            }
        });
    }
}
