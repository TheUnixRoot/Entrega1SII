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
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.omnifaces.cdi.ViewScoped;

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
    boolean editar;
    /**
     * Creates a new instance of Mod_usuariosBean
     */
    @PostConstruct
    public void init() {
        Map<String, String> req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.setId(Long.parseLong(req.get("id")));
        this.setEditar(Boolean.getBoolean(req.get("edit")));
        System.out.println(editar);
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

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    
}
