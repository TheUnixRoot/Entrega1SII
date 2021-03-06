/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.entregajsf.dropbox.DropboxController;
import grupoj.entregajsf.dropbox.DropboxControllerException;
import grupoj.prentrega1.*;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author juanp
 */
@Named(value = "persistenceMock")
@ApplicationScoped
public class PersistenceMock implements Serializable {
    
    private List<Usuario> listaUsuarios;


    private Formulario formulario;
    


    private List<Mensaje> listaMensajes;
    
    /*public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    public void addMessage(Mensaje msg){
        listaMensajes.add(msg);
    }*/

    private List<Evento> listaEventos;
    private List<Lugar> listaLugares;
    private List<Tag> listaTags;
    private List<Tag> listaTags2;
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
        listaTags2 = new ArrayList<>();
        
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setTexto("Música");
        listaTags.add(tag1);
        
        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setTexto("Teatro");
        listaTags.add(tag2);
        
        Tag tag3 = new Tag();
        tag3.setId(3L);
        tag3.setTexto("Arte");
        listaTags.add(tag3);
        
        Tag tag4 = new Tag();
        tag3.setId(4L);
        tag3.setTexto("Ópera");
        listaTags.add(tag4);
        
        Tag tag5 = new Tag();
        tag3.setId(5L);
        tag3.setTexto("Cine");
        listaTags.add(tag5);
        
        Tag tag6 = new Tag();
        tag3.setId(6L);
        tag3.setTexto("Deportes");
        listaTags.add(tag6);
       
        
        
        
        listaTags2.add(tag1);
        listaTags2.add(tag3);
        
        
        formulario = new Formulario();
        formulario.setForm_tags(listaTags);

        Usuario usr = new Usuario();
        usr.setId(1L);
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        try {
            usr.setMultimedia(
                    DropboxController.downloadFile("/usuario.jpeg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        usr.setForm(formulario);

        //usr.setForm(this.formulario);
        usr.setMsg_send(listaMensajes);

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
        try {
            per.setMultimedia(
                    DropboxController.downloadFile("/peri.jpeg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
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
        try {
            adm.setMultimedia(
                    DropboxController.downloadFile("/lisa.png"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaUsuarios.add(adm);
        
        Geolocalizacion geo = new Geolocalizacion();
        Lugar lug = new Lugar();
        
        geo.setDireccion("NombreCalle");
        lug.setNombre("Recinto ferial");
        lug.setBorrado(false);
        geo.setLugar(lug);
        lug.setGeolocalizacion(geo);
        
        Lugar lug2 = new Lugar();
        Geolocalizacion geo2 = new Geolocalizacion();
        geo2.setDireccion("Nombre de la calle");
        lug2.setNombre("Recinto ferial");
        lug2.setBorrado(false);
        geo2.setLugar(lug2);
        lug.setGeolocalizacion(geo2);
        
        
        Evento e = new Evento();
        e.setNombre("Feria Málaga");
        e.setBorrado(false);
        e.setValidado(true);
        e.setDescripcion("Feria de malaga 2017");
        e.setPrecio(20);
        e.setDonde_comprar("www.malaga.com");
        e.setTagged_by(listaTags);
        e.setOcurre_in(lug);
        e.setId(25L);
        e.setFecha_inicio(new Date());

        e.setFecha_fin(new Date());

        listaEventos.add(e);
        
        Evento e2 = new Evento();
        e2.setNombre("Concierto de alguien");
        e2.setBorrado(false);
        e2.setValidado(true);
        e2.setDescripcion("Concierto de alguien muy muy famoso");
        e2.setPrecio(50);
        e2.setDonde_comprar("www.antequera.com");
        e2.setTagged_by(listaTags2);
        e2.setOcurre_in(lug2);
        e2.setId(25L);
        e2.setFecha_inicio(new Date());

        e2.setFecha_fin(new Date());

        listaEventos.add(e2);
        
        Notificacion ne = new Notificacion();
        ne.setId(System.currentTimeMillis());
        ne.setContenido("Holi");
        
        adm.setNotificaciones(new ArrayList<Notificacion>());
        adm.getNotificaciones().add(ne);
        
        
       /* Geolocalizacion geo1 = new Geolocalizacion();
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
        listaEventos.add(ev);*/
        
        Anuncio adv = new Anuncio();
        adv.setId(10L);
        adv.setOnline(true);
        adv.setLugar("top");
        adv.setEmpresa("Aliexpress");
        adv.setAdmin(adm);
        adv.setFecha_public(new Date());
        adv.setDias_contratados(100);
        try {
            adv.setMultimedia(
                    DropboxController.downloadFile("/amazon.png"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaAnuncios.add(adv);
        

        Anuncio adv2 = new Anuncio();
        adv2.setId(11L);
        adv2.setOnline(true);
        adv2.setLugar("bot");
        adv2.setEmpresa("Aliexpress");
        adv2.setAdmin(adm);
        adv2.setFecha_public(new Date());
        adv2.setDias_contratados(100);
        try {
            adv2.setMultimedia(
                    DropboxController.downloadFile("/imagen1.png"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        
        listaAnuncios.add(adv2);
        
        Anuncio adv3 = new Anuncio();
        adv3.setId(13L);
        adv3.setOnline(true);
        adv3.setLugar("self");
        adv3.setEmpresa("SUR");
        adv3.setAdmin(adm);
        adv3.setFecha_public(new Date());
        adv3.setDias_contratados(100);
        try {
            adv3.setMultimedia(
                    DropboxController.downloadFile("/default.jpg"));
        } catch (DropboxControllerException ex) {
            ex.printStackTrace();
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaAnuncios.add(adv3);
        
        Anuncio adv4 = new Anuncio();
        adv4.setId(14L);
        adv4.setOnline(false);
        adv4.setLugar("top");
        adv4.setEmpresa("Razer");
        adv4.setAdmin(adm);
        adv4.setFecha_public(new Date());
        adv4.setDias_contratados(100);
        try {
            adv4.setMultimedia(
                    DropboxController.downloadFile("/Razer1.jpg"));
        } catch (DropboxControllerException ex) {
            System.err.println("Error al acceder al recurso en linea " + ex.getMessage());
        }
        listaAnuncios.add(adv4);

        Lugar lugar1 = new Lugar();
        lugar1.setId(1L);
        lugar1.setNombre("plazuela");
        lugar1.setDescripcion("Un sitio muy chu-chuli");
        listaLugares.add(lugar1);
        
        Lugar lugar2 = new Lugar();
        lugar2.setId(2L);
        lugar2.setNombre("campo futbol");
        lugar2.setDescripcion("estadio grande");
        listaLugares.add(lugar2);

        
        
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

    public void setListaEventos(List<Evento> listaEventos) throws InterruptedException {
        mutexEventos.acquire();
        this.listaEventos = listaEventos;
        mutexEventos.release();
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

