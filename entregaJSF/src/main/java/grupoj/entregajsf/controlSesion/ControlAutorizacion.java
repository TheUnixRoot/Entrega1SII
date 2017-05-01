/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.controlSesion;


import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Periodista;
import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.concurrent.Semaphore;
import javax.faces.context.FacesContext;

/**
 *
 * @author francis
 */
@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Usuario usuario;
    private Semaphore mutex = new Semaphore(1);
    
    public void setUsuario(Usuario usuario) throws InterruptedException {
        mutex.acquire();
        this.usuario = usuario;
        mutex.release();
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public String logout() throws InterruptedException
    {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        mutex.acquire();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        
        usuario = null;
        mutex.release();
        return "login.xhtml";
    }

    /**
     * Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }
    
    public boolean isAdministrador() {
        return this.usuario instanceof Administrador;
    }
    
    public boolean isPeriodista() {
        return this.usuario instanceof Periodista;
    }
    
}
