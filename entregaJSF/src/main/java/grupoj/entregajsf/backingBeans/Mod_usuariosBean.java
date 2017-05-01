/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.entregajsf.dropbox.DropboxController;
import grupoj.entregajsf.dropbox.DropboxControllerException;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "mod_usuariosBean")
@ViewScoped
public class Mod_usuariosBean implements Serializable {

    @Inject
    PersistenceMock persistencia;
    @Inject
    ControlAutorizacion controlAutorizacion;
    Usuario usr;
    long id;
    /**
     * Creates a new instance of Mod_usuariosBean
     */
    @PostConstruct
    public void init() {
        Map<String, String> req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.setId(Long.parseLong(req.get("id")));
        Usuario uuu = new Usuario();
        uuu.setId(id);
        System.out.println(id);
        if ( this.persistencia.getListaUsuarios().contains(uuu) ) 
            this.usr = this.persistencia.getListaUsuarios()
                    .get(
                            this.persistencia.getListaUsuarios().indexOf(uuu)
                    );
        else
            this.usr = null;
        uuu = null;
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public ControlAutorizacion getControlAutorizacion() {
        return controlAutorizacion;
    }

    public void setControlAutorizacion(ControlAutorizacion controlAutorizacion) {
        this.controlAutorizacion = controlAutorizacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public StreamedContent generar() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            Usuario uu = new Usuario();
            uu.setId(Long.parseLong(params.get("id")));
            String mul = persistencia
                    .getListaUsuarios()
                    .get(persistencia
                            .getListaUsuarios()
                            .indexOf(uu)
                    )
                    .getMultimedia();
            if (mul == null) mul = "/default.jpg";
            con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile(mul))); 
            
        } catch (DropboxControllerException dbex) {
            try {
                con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile("/default.jpg")));
            } catch (DropboxControllerException ex) {
                Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id usuario recibido " + params.get("id"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("id") + " excep: " + ne.getMessage());
        }
        return con;
    }
    
}