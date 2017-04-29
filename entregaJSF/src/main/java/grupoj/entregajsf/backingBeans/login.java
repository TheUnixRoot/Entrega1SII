package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import grupoj.prentrega1.Usuario;
import javax.annotation.PostConstruct;
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
    @Inject
    private PersistenceMock persistencia;
    
    @Inject
    private ControlAutorizacion ctrl;

    /**
     * Creates a new instance of Login
     */
    @PostConstruct
    public void init() {
        usuarios = persistencia.getListaUsuarios();
       /* usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("pepe", "asdf", Rol.NORMAL));
        usuarios.add(new Usuario("manolo", "qwer", Rol.ADMINISTRADOR));*/
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

    public String autenticar() throws InterruptedException {
        Usuario usu = new Usuario();
        usu.setEmail(email);
        usu.setPassword(contrasenia);
        usuarios = persistencia.getListaUsuarios();
        boolean encontrado = false;
        for(Usuario u : usuarios){
            if(u.getEmail().equalsIgnoreCase(usu.getEmail()) && u.getPassword().equals(usu.getPassword())){
                encontrado = true;
                ctrl.setUsuario(u);
            }
        }
        
        if(encontrado){
            return "index.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario/contraseña incorrectos", "Usuario/contraseña incorrectos"));
         
        return null;
    }
    
    
    
}
