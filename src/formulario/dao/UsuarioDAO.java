/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.dao;

import formulario.entidade.Usuario;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author zecaubun
 */
public class UsuarioDAO {
    
    public boolean salvaUsuario(Usuario user){
        boolean confirmacao = true;
        EntityManager em = FabricaEntityManager.retornaConexao();
        
        try{
            em.getTransaction().begin();
            if(user.getId() == null)
                em.persist(user);
            else
                em.merge(user);
            em.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e.getMessage());
            em.getTransaction().rollback();
            confirmacao = false;
        }
        finally{
            em.close();
        }
        return confirmacao;
    }
   
    
    public boolean removeUsuario(Usuario user){
        boolean confirmacao = true;
        EntityManager em = FabricaEntityManager.retornaConexao();
        
        Usuario us = em.find(Usuario.class, user.getId());
        try{
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e.getMessage());
            em.getTransaction().rollback();
            confirmacao = false;
        }
        finally{
            em.close();
        }
        return confirmacao;
    }
    public Usuario encontraUsuario(String nome){
        EntityManager em = FabricaEntityManager.retornaConexao();
        
        Usuario user = null;
        try{
            user = em.find(Usuario.class, nome);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            em.close();
        }
        return user;
    }
    public List<Usuario> encontraTodosUsuarios(){
        EntityManager em = FabricaEntityManager.retornaConexao();
        
        List<Usuario> lista = null;
        try{
            lista = em.createQuery("from Usuario u").getResultList();
            
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            em.close();
        }
        return Collections.unmodifiableList(lista);
    }
}
