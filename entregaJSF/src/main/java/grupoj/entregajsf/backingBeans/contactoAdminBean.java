/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Administrador;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
    private Usuario user ;
    private List<Usuario> listUsers;
    private List<Administrador> admins;
    @Inject
    private ControlAutorizacion control;
    /**
     * Creates a new instance of contactoAdminBean
     */
    @PostConstruct
    public void init() {
        persistencia = new PersistenceMock();
        listUsers = persistencia.getListaUsuarios();
        user = control.getUsuario();        
        message = new Mensaje();
        admins = new ArrayList();
        
        for(Usuario u : listUsers){
            if(u instanceof Administrador){
                admins.add((Administrador) u);
            }
        }
       
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
    
    
     public String crearMensaje() {       
        
        message.setTexto(this.texto);
        message.setAsunto(this.asunto);
        message.setEnviadoPor(user);
        message.setRecibidoPor(admins);
        for(Administrador admin : admins){
            if(admin.getRecibirMensaje()==null){
                List<Mensaje> listaMensajes = new ArrayList<>();
                admin.setRecibirMensaje(listaMensajes);
             }
            admin.getRecibirMensaje().add(message);
        }
        if(user.getMsg_send()==null){
            List<Mensaje> listaMensajes = new ArrayList<>();
            user.setMsg_send(listaMensajes);
        }
        user.getMsg_send().add(message);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage("formulario:panel:growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje enviado correctamente", "Mensaje enviado correctamente"));
        return "index.xhtml";
     }

}