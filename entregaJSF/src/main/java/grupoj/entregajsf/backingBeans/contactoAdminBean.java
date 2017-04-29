/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Administrador;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
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
    private Usuario user ;
    private List<Usuario> listUsers;
    private List<Administrador> admins;
    /**
     * Creates a new instance of contactoAdminBean
     */
    public contactoAdminBean() {
        persistencia = new PersistenceMock();
        listUsers = persistencia.getListaUsuarios();
        user = listUsers.get(0);
        message = new Mensaje();
        admins = new ArrayList();
        for(Usuario u : listUsers){
            if(u.getClass().equals(Administrador.class)){
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
    
    
    
     public void crearMensaje() {
       
        
        message.setTexto(this.texto);
        message.setAsunto(this.asunto);
        message.setEnviadoPor(user);
        message.setRecibidoPor(admins);
        for(Administrador admin : admins){
            admin.getRecibirMensaje().add(message);
        }
        user.getMsg_send().add(message);
        persistencia.addMessage(message);    
        String msg = persistencia.getListaMensajes().get(0).getTexto();
        System.out.println(msg);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }
 
      public String goIndex(){
        return "index.xhtml";
    }
 
}
