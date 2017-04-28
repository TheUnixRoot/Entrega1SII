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
import grupoj.prentrega1.*;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juanp
 */
@Named(value = "persistenceMock")
@SessionScoped
public class PersistenceMock implements Serializable {
    
    private List<Usuario> listaUsuarios;
    private List<Evento> listaEventos;
    private Lugar lugar1;
    private List<Tag> listaTags;
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
<<<<<<< HEAD
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
        
=======
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
        ev.setId(1L);
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
        adv.setMultimedia("media/adverts/patata.png");
        listaAnuncios.add(adv);
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
    
>>>>>>> master
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
<<<<<<< HEAD
=======

>>>>>>> master
    public List<Evento> getListaEventos() {
        return listaEventos;
    }

<<<<<<< HEAD
    public void setListaEventos(List<Evento> listaeventos) {
        this.listaEventos = listaeventos;
=======
    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
>>>>>>> master
    }
    
}
