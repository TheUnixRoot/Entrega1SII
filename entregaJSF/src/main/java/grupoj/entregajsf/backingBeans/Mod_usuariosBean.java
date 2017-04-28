/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

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
    /**
     * Creates a new instance of Mod_usuariosBean
     */
    public Mod_usuariosBean() {
        usr = controlAutorizacion.getUsuario();
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
    
}
