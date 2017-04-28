/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.*;
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
    private List<Evento> listaEventos;
    private Lugar lugar1;
    private List<Tag> listaTags;
    
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
        usr.setMultimedia(null);
        listaUsuarios.add(usr);
        
        
        Geolocalizacion geo = new Geolocalizacion();
        geo.setDireccion("NombreCalle");
        geo.setLugar(lugar1);
        
        lugar1 = new Lugar();
        lugar1.setNombre("Recinto ferial");
        lugar1.setGeolocalizacion(geo);
        
        Tag tag1 = new Tag();
        tag1.setTexto("Música");
        Tag tag2 = new Tag();
        tag2.setTexto("Teatro");
        listaTags = new ArrayList<Tag>();
        listaTags.add(tag1);
        listaTags.add(tag2);
        
        listaEventos = new ArrayList<>();
        Evento e = new Evento();
        e.setNombre("Feria Málaga");
        
        e.setBorrado(false);
        e.setValidado(true);
        e.setDescripcion("Feria de malaga 2017");
        e.setPrecio(20);
        e.setDonde_comprar("www.malaga.com");
        e.setTagged_by(listaTags);
        e.setOcurre_in(lugar1);
        listaEventos.add(e);
        
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

    public void setListaEventos(List<Evento> listaeventos) {
        this.listaEventos = listaeventos;
    }
    
}
