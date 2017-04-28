/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import javax.annotation.PostConstruct;

/**
 *
 * @author juanp
 */
@Named(value = "mod_usuariosBean")
@RequestScoped
public class Mod_usuariosBean {

    @Inject
    PersistenceMock persistencia;
    @Inject
    ControlAutorizacion controlAutorizacion;
    Usuario usr;
    long id;
    /**
     * Creates a new instance of Mod_usuariosBean
     */
    public void init() {
        Usuario uuu = new Usuario();
        uuu.setId(id);
        System.out.println(id);
        if ( this.persistencia.getListaUsuarios().contains(uuu) ) {
            this.usr = this.persistencia.getListaUsuarios()
                    .get(
                            this.persistencia.getListaUsuarios().indexOf(uuu)
                    );
            System.out.println("Hay useeer");
        } else
            this.usr = null;
        System.out.println("NO HAY useeer");
       
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
}
