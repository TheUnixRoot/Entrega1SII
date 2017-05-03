/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author juanp
 */
@Named(value = "signupBean")
@RequestScoped
public class SignupBean {
    
    @Inject
    PersistenceMock persistencia;
    Usuario usuario;
    
    /**
     * Creates a new instance of SignupBean
     */
    public SignupBean() {
        usuario = new Usuario();
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return usuario.getPassword();
    }

    public void setPass(String pass) {
        this.usuario.setPassword(pass);
    }

    public String getNombre() {
        return usuario.getNombre();
    }

    public void setNombre(String nombre) {
        this.usuario.setNombre(nombre);
    }
    
    public String getApellidos() {
        return usuario.getApellidos();
    }

    public void setApellidos(String apellidos) {
        this.usuario.setApellidos(apellidos);
    }
    
    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String email) {
        this.usuario.setEmail(email);
    }
    
    public String getTelefono() {
        return usuario.getTelefono();
    }

    public void setTelefono(String telefono) {
        this.usuario.setTelefono(telefono);
    }
    
    public Date getFechaNacimiento() {
        return usuario.getFechaNacimiento();
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.usuario.setFechaNacimiento(fechaNacimiento);
    }
    
    public String submit() {
        List<Usuario> list = persistencia.getListaUsuarios();
        usuario.setId((long)list.size());
        list.add(usuario);
        try {
            persistencia.setListaUsuarios(list);
        } catch (InterruptedException ex) {
            System.err.println("Error al insertar usuario en persistencia");
        }
        
        return "index.xhtml";
    }
}
