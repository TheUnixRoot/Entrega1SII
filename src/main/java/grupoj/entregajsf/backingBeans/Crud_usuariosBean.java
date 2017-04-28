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

/**
 *
 * @author juanp
 */
@Named(value = "crud_usuariosBean")
@RequestScoped
public class Crud_usuariosBean {

    private PersistenceMock persistencia;
    private Iterable<Usuario> usuarios;
    
    /**
     * Creates a new instance of Crud_usuariosBean
     */
    public Crud_usuariosBean() {
        persistencia = new PersistenceMock();
        usuarios = persistencia.getListaUsuarios();
    }

    public Iterable<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Iterable<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
