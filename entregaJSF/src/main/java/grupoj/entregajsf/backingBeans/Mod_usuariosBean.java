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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "mod_usuariosBean")
@Dependent
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
            byte[] mul = persistencia
                    .getListaUsuarios()
                    .get(persistencia
                            .getListaUsuarios()
                            .indexOf(uu)
                    )
                    .getMultimedia();
            con = new DefaultStreamedContent(new ByteArrayInputStream(mul)); 
            
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id usuario recibido " + params.get("id"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("id") + " excep: " + ne.getMessage());
        }
        return con;
    }
    public void change() {
        System.out.println("Changeeeeeeeee");
        this.usr.getId();
        Iterator<Usuario> it = persistencia.getListaUsuarios().iterator();
        boolean find = false;
        Usuario uu = null;
        while(it.hasNext() && !find) {
            uu = it.next();
            if (uu.equals(usr)) {
                int l = persistencia.getListaUsuarios().indexOf(uu);
                List<Usuario> list = persistencia.getListaUsuarios();
                list.set(l, usr);
                try {
                    persistencia.setListaUsuarios(list);
                } catch (InterruptedException ex) {
                    System.err.println("Error al modificar el usuario");
                }
                find = true;
            }
        }
    }
}