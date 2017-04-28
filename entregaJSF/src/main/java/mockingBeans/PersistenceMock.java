/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author juanp
 */
@Named(value = "persistenceMock")
@RequestScoped
public class PersistenceMock implements Serializable {
    
    private List<Usuario> listaUsuarios;
    private List<Notificacion> listaNotificaciones;
    private List<Formulario> listaFormularios;
    private TipoNotificacion tipoNotUsuario;
    /*
    Crear lista de notificaciones magicamente y haces magia con ella
    */
    
    /** 
     * Creates a new instance of PersistenceMock
     */
    public PersistenceMock() {
        listaUsuarios = new ArrayList<>();
        Usuario usr = new Usuario();
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Desactivado);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        usr.setMultimedia("none");
        listaUsuarios.add(usr);
        
        //listaNotificaciones = new ArrayList<>();
        //Notificacion ntf = new Notificacion();
        //NPI
        
        // Tipo de notificacion
        tipoNotUsuario = usr.getTipoNotificacionesRecibir();
        
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }

    public List<Formulario> getListaFormularios() {
        return listaFormularios;
    }

    public void setListaFormularios(List<Formulario> listaFormularios) {
        this.listaFormularios = listaFormularios;
    }

    public TipoNotificacion getTipoNotUsuario() {
        return tipoNotUsuario;
    }

    public void setTipoNotUsuario (TipoNotificacion tipoNotUsuario) {
        this.tipoNotUsuario = tipoNotUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
