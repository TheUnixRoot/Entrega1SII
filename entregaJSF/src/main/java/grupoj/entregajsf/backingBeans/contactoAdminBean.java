/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import grupoj.prentrega1.Mensaje;
import javax.enterprise.context.RequestScoped;
import mockingBeans.PersistenceMock;

/**
 *
 * @author JesusAlberto
 */
@RequestScoped
@Named(value = "contactoAdminBean")
public class contactoAdminBean {

    private PersistenceMock persistencia;
    private Mensaje message;
    private String texto;
    private String asunto;
    //private Usuario user;
    /**
     * Creates a new instance of contactoAdminBean
     */
    public contactoAdminBean() {
        persistencia = new PersistenceMock();
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    
    
     public void crearMensaje() {
       
        message = new Mensaje();
        message.setTexto(this.texto);
        message.setAsunto(this.asunto);
        persistencia.addMessage(message);       
     
    }
 
 
}
