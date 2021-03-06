/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.dropbox.DropboxController;
import grupoj.entregajsf.dropbox.DropboxControllerException;
import grupoj.prentrega1.Usuario;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "crud_usuariosBean")
@Dependent
public class Crud_usuariosBean implements Serializable{
    
    @Inject
    private PersistenceMock persistencia;
    
    public List<Usuario> getUsuarios() {
        return persistencia.getListaUsuarios();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        try {
            this.persistencia.setListaUsuarios(usuarios);
        } catch (InterruptedException ex) {
            Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return params.get("editar").equals("true") ?("edit_usuario.xhtml?id=" + params.get("id")) : ("read_usuario.xhtml?id=" + params.get("id"));
    }
    
    public StreamedContent generar(Usuario usu) {
        StreamedContent con = null;
        byte[] mul = usu.getMultimedia();
        if(mul == null) {
            return null;
        }
        try {
            con = new DefaultStreamedContent(new ByteArrayInputStream(usu.getMultimedia())); 
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id usuario recibido " + usu.getId());
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + usu.getId() + " excep: " + ne.getMessage());
        }
        return con;
    }  
}

