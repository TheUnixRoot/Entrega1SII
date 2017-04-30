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
import java.util.concurrent.Semaphore;
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
    private List<Lugar> listaLugares;
    private List<Tag> listaTags;
    private List<Anuncio> listaAnuncios;
    
    private Semaphore mutexUsuarios;
    private Semaphore mutexEventos;
    private Semaphore mutexLugares;
    private Semaphore mutexTags;
    private Semaphore mutexAnuncios;
    
    
    /**
     * Creates a new instance of PersistenceMock
     */
    public PersistenceMock() {
        listaUsuarios = new ArrayList<>();
        listaEventos = new ArrayList<>();
        listaAnuncios = new ArrayList<>();
        listaLugares = new ArrayList<>();
        listaTags = new ArrayList<>();
        
        Tag tag1 = new Tag();
        tag1.setTexto("Música");
        listaTags.add(tag1);
        
        Tag tag2 = new Tag();
        tag2.setTexto("Teatro");
        listaTags.add(tag2);
        
        Usuario usr = new Usuario();
        usr.setId(1L);
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        usr.setMultimedia("/usuario.jpeg");
        listaUsuarios.add(usr);
       
        Periodista per = new Periodista();
        per.setId(2L);
        per.setSeccion("Cultura");
        per.setPuesto("Freelance");
        per.setEmail("usuario@periodista.com");
        per.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        per.setPassword("periodista");
        per.setBorrado(false);
        per.setNombre("periodisto");
        per.setMultimedia(null);
        listaUsuarios.add(per);
        
        Administrador adm = new Administrador();
        adm.setId(3L);
        adm.setIdentificador(1L);
        adm.setSeccion("Cultura");
        adm.setPuesto("Administrador");
        adm.setEmail("usuario@administrador.com");
        adm.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        adm.setPassword("administrador");
        adm.setBorrado(false);
        adm.setNombre("administradorcito");
        adm.setMultimedia(null);
        listaUsuarios.add(adm);
        
        Geolocalizacion geo = new Geolocalizacion();
        Lugar lug = new Lugar();
        
        geo.setDireccion("NombreCalle");
        lug.setNombre("Recinto ferial");
        lug.setBorrado(false);
        geo.setLugar(lug);
        lug.setGeolocalizacion(geo);
        
        Evento e = new Evento();
        e.setNombre("Feria Málaga");
        e.setBorrado(false);
        e.setValidado(true);
        e.setDescripcion("Feria de malaga 2017");
        e.setPrecio(20);
        e.setDonde_comprar("www.malaga.com");
        e.setTagged_by(listaTags);
        e.setOcurre_in(lug);
        listaEventos.add(e);
        
        Geolocalizacion geo1 = new Geolocalizacion();
        Lugar lug1 = new Lugar();
        
        geo1.setDireccion("Bulevar Luis Pasteur, 35, campus de Teatinos, 29071, Malaga");
        lug1.setBorrado(false);
        lug1.setNombre("ETSI Informatica");
        lug1.setGeolocalizacion(geo1);
        geo1.setLugar(lug1);
        
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
        
        mutexUsuarios = new Semaphore(1);
        mutexEventos = new Semaphore(1);
        mutexLugares = new Semaphore(1);
        mutexTags = new Semaphore(1);
        mutexAnuncios = new Semaphore(1);
        
        System.out.println("Persistencia creada en Singleton");
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) throws InterruptedException {
        mutexUsuarios.acquire();
        this.listaUsuarios = listaUsuarios;
        mutexUsuarios.release();
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) throws InterruptedException {
        mutexAnuncios.acquire();
        this.listaAnuncios = listaAnuncios;
        mutexAnuncios.release();
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException {
        mutexLugares.acquire();
        this.listaLugares = listaLugares;
        mutexLugares.release();
    }

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tag> listaTags) throws InterruptedException {
        mutexTags.acquire();
        this.listaTags = listaTags;
        mutexTags.release();
    }
    
}
