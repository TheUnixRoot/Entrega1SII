/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Anuncio;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Periodista;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author juanp
 */
@Named(value = "persistenceMock")
@SessionScoped
public class PersistenceMock implements Serializable {
    
    private List<Usuario> listaUsuarios;
    private List<Evento> listaEventos;
    private List<Anuncio> listaAnuncios;
    
    /**
     * Creates a new instance of PersistenceMock
     */
    public PersistenceMock() {
        listaUsuarios = new ArrayList<>();
        listaEventos = new ArrayList<>();
        listaAnuncios = new ArrayList<>();
        
        Usuario usr = new Usuario();
        
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        usr.setMultimedia("none");
        
        listaUsuarios.add(usr);
        Periodista per = new Periodista();
        
        per.setSeccion("Cultura");
        per.setPuesto("Freelance");
        
        per.setEmail("usuario@periodista.com");
        per.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        per.setPassword("periodista");
        per.setBorrado(false);
        per.setNombre("periodisto");
        per.setMultimedia("none");
        listaUsuarios.add(per);
        
        Administrador adm = new Administrador();
        
        adm.setIdentificador(1L);
        
        adm.setSeccion("Cultura");
        adm.setPuesto("Administrador");
        
        adm.setEmail("usuario@administrdor.com");
        adm.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        adm.setPassword("administrador");
        adm.setBorrado(false);
        adm.setNombre("administradorcito");
        adm.setMultimedia("none");
        listaUsuarios.add(adm);
        
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
        
        Anuncio adv = new Anuncio();
        
        adv.setAdmin(adm);
        adv.setFecha_public(new Date());
        adv.setDias_contratados(100);
        adv.setMultimedia("/media/adverts/patata.png");
        listaAnuncios.add(adv);
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

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }
    
}
