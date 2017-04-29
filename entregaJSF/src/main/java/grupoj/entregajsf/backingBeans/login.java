package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import grupoj.prentrega1.Usuario;
import mockingBeans.PersistenceMock;

/**
 *
 * @author anaes
 */
@Named(value = "login")
@RequestScoped
public class login {

    private String email;
    private String contrasenia;
    private List<Usuario> usuarios;
    private PersistenceMock persistencia = new PersistenceMock();
    
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    public login() {
        persistencia = new PersistenceMock();
        usuarios = persistencia.getListaUsuarios();
    }
    public String getEmail() {
        return email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String autenticar() {
        Usuario usu = new Usuario();
        usu.setEmail(email);
        usu.setPassword(contrasenia);
        usuarios = persistencia.getListaUsuarios();
        FacesContext ctx = FacesContext.getCurrentInstance();
        Boolean encontrado = false;
        for(Usuario u : usuarios){
            if(u.getEmail().equalsIgnoreCase(usu.getEmail()) && u.getPassword().equals(usu.getPassword())){
                encontrado = true;
                ctrl.setUsuario(u);
            }
        }
        
        if(encontrado == true){
            return "index.xhtml";
        }
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario/contraseña incorrectos", "Usuario/contraseña incorrectos"));
        
        return null;
    }
    
    
    
}
