/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Administrador;
import javax.inject.Named;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import mockingBeans.PersistenceMock;

/**
 *
 * @author JesusAlberto
 */

@Named(value = "contactoAdminBean")
public class contactoAdminBean {

    private PersistenceMock persistencia;
    private Mensaje message;
    private String texto;
    private String asunto;
    private Usuario user ;
    private List<Usuario> listUsers;
    private List<Administrador> admins;
    private ControlAutorizacion control;
    /**
     * Creates a new instance of contactoAdminBean
     */
    public contactoAdminBean() {
        persistencia = new PersistenceMock();
        listUsers = persistencia.getListaUsuarios();
        control = new ControlAutorizacion();
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
    
    
    
     public void crearMensaje() {
       
        
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
       
    }
 
      public String goIndex(){
        return "index.xhtml";
    }
 
}
