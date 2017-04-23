/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Periodista;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private List<Evento> listaEventos;
    
    /**
     * Creates a new instance of PersistenceMock
     */
    public PersistenceMock() {
        listaUsuarios = new ArrayList<>();
        Usuario usr = new Usuario();
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        usr.setMultimedia("none");
        
        listaUsuarios.add(usr);
        usr = new Periodista();
        usr.setEmail("usuario@periodista.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("periodista");
        usr.setBorrado(false);
        usr.setNombre("periodisto");
        usr.setMultimedia("none");
        listaUsuarios.add(usr);
        
        usr = new Administrador();
        usr.setEmail("usuario@administrdor.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("administrador");
        usr.setBorrado(false);
        usr.setNombre("administradorcito");
        usr.setMultimedia("none");
        listaUsuarios.add(usr);
        
        Lugar lug = new Lugar();
        Geolocalizacion geo = new Geolocalizacion();
        
        geo.setDireccion("Bulevar Luis Pasteur, 35, campus de Teatinos, 29071, Malaga");
        
        lug.setBorrado(false);
        lug.setNombre("ETSI Informatica");
        lug.setGeolocalizacion(geo);
        lug.setGeolocalizacion(geo);
        geo.setLugar(lug);
        
        Evento ev = new Evento();
        ev.setNombre("Evento 1");
        ev.setBorrado(false);
        ev.setValidado(true);
        ev.setFecha(new Date());
        ev.setOcurre_in(lug);
        
        listaEventos.add(ev);
        
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }
    
}