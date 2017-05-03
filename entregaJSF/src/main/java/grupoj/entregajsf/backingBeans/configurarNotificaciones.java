/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author David
 * Ahora mismo falta de todo.... 
 * Mirar la configuración que tiene el usuario, cargarla y despues
 * enviar en el caso de que haya cambios
 */
@Named(value = "configurarNotificaciones")
@Dependent
public class configurarNotificaciones {
    @Inject
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion ctrAut;
    private String notificacionesActivas;
    private Usuario usuLogueado;    // Recoger el usuario que se está logueado
    private TipoNotificacion tipoNotUsuario;
    private String notificacion;
    private List<String> listaNotif;

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
    
    public String getNotificacionesActivas() {
        return notificacionesActivas;
    }

    public void setNotificacionesActivas(String notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
        System.out.println("Ha cambiado: "+this.notificacionesActivas);
    }

    public Usuario getUsuLogueado() {
        return usuLogueado;
    }

    public void setUsuLogueado(Usuario usuLogueado) {
        this.usuLogueado = usuLogueado;
    }

    public TipoNotificacion getTipoNotUsuario() {
        return tipoNotUsuario;
    }

    public void setTipoNotUsuario(TipoNotificacion tipoNotUsuario) {
        this.tipoNotUsuario = tipoNotUsuario;
    }

    public List<String> getListaNotif() {
        return listaNotif;
    }

    public void setListaNotif(List<String> listaNotif) {
        this.listaNotif = listaNotif;
    }
    
    @PostConstruct
    public void init() {
        usuLogueado = ctrAut.getUsuario(); // Usuario que se ha logueado, ahora esta el de persistencia.
        tipoNotUsuario = usuLogueado.getTipoNotificacionesRecibir();
        
        listaNotif = new ArrayList<>();
        rellenaLista();
    }

    private void insertaLista(TipoNotificacion n) {
        switch(n) {
            case Email: 
                listaNotif.add("Email");
                break;
                
            case Cuenta: 
                listaNotif.add("Cuenta");
                break; 
                
            case Ambos: 
                listaNotif.add("Ambos");
                break;  
              
        }
    }
    
    private void rellenaLista () {
        
        if (tipoNotUsuario == TipoNotificacion.Desactivado) {
            notificacionesActivas = "1";
        } else {
            notificacionesActivas = "0";
        }
        
        insertaLista(tipoNotUsuario);
        if (tipoNotUsuario != TipoNotificacion.Email) {
            insertaLista(TipoNotificacion.Email);
        }
        
        if (tipoNotUsuario != TipoNotificacion.Cuenta) {
            insertaLista(TipoNotificacion.Cuenta);
        }
        
        if (tipoNotUsuario != TipoNotificacion.Ambos) {
            insertaLista(TipoNotificacion.Ambos);
        }
        
    }
    
    public String tratarInformacion() throws InterruptedException {
        if (notificacion == null) {
            notificacion = "Desactivado";
        }
        switch (notificacion) {
            case "Email":
                tipoNotUsuario = TipoNotificacion.Email;
                break;
            case "Cuenta":
                tipoNotUsuario = TipoNotificacion.Cuenta;
                break;
            case "Ambos":
                tipoNotUsuario = TipoNotificacion.Ambos;
                break;
            default: 
                break;
        }

        System.out.println("Esta activa: "+notificacionesActivas);
        if (notificacionesActivas.equals("1")) {
            this.tipoNotUsuario = TipoNotificacion.Desactivado;
        } 
        
        if (this.tipoNotUsuario != usuLogueado.getTipoNotificacionesRecibir()) {
            Usuario usuPrima = usuLogueado;
            usuPrima.setTipoNotificacionesRecibir(tipoNotUsuario);
            List<Usuario> listaUsu = this.persistencia.getListaUsuarios();
            listaUsu.remove(usuLogueado);
            listaUsu.add(usuPrima);
            this.persistencia.setListaUsuarios(listaUsu);
            ctrAut.setUsuario(usuPrima);
        }
        
        return null;
    }
    
}
