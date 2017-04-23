package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author anaes
 */
@Named(value = "login")
@RequestScoped
public class login {

    private String usuario;
    private String contrasenia;
    private List<Usuario> usuarios;
    
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    public login() {
       /* usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("pepe", "asdf", Rol.NORMAL));
        usuarios.add(new Usuario("manolo", "qwer", Rol.ADMINISTRADOR));*/
    }
    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String autenticar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Usuario usu = null;
        for(Usuario u : usuarios){
            if(u.getUsuario().equals(this.getUsuario()) && u.getContrasenia().equals(this.getContrasenia())){
                usu=u;
                ctrl.setUsuario(u);
            }
        }
        
        if(usu != null){
            return ctrl.home();
        }
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario/contraseña incorrectos", "Usuario/contraseña incorrectos"));
    
            return null;
            
        }
    
    
    
}
