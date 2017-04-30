/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;


//<<<<<<< HEAD
/*import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Mensaje;
import grupoj.prentrega1.Tag;
//=======
=======
/*<<<<<<< HEAD
import grupoj.prentrega1.Mensaje;
=======
>>>>>>> contactarAdmin_Jesus
import grupoj.prentrega1.Administrador;
import grupoj.prentrega1.Anuncio;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Periodista;
<<<<<<< HEAD
//>>>>>>> master
=======
>>>>>>> master
>>>>>>> contactarAdmin_Jesus
import grupoj.prentrega1.TipoNotificacion;*/
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
//<<<<<<< HEAD

    //private List<Mensaje> listaMensajes;
    //private Formulario formulario;

    

    //private List<Mensaje> listaMensajes;
    
    /*public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    public void addMessage(Mensaje msg){
        listaMensajes.add(msg);
    }*/

//=======
    private List<Evento> listaEventos;
    private List<Lugar> listaLugares;
    private List<Tag> listaTags;
    private List<Anuncio> listaAnuncios;
//>>>>>>> master
    
    /**
     * Creates a new instance of PersistenceMock
     */
    public PersistenceMock() {
        listaUsuarios = new ArrayList<>();
//<<<<<<< HEAD

        //formulario = new Formulario();
        //listaMensajes = new ArrayList<>();

//=======
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
        
//>>>>>>> master
        Usuario usr = new Usuario();
        usr.setId(1L);
        usr.setEmail("usuario@normal.com");
        usr.setTipoNotificacionesRecibir(TipoNotificacion.Ambos);
        usr.setPassword("usuario");
        usr.setBorrado(false);
        usr.setNombre("normalito");
        usr.setMultimedia("none");

        //usr.setForm(this.formulario);
        //usr.setMsg_send(listaMensajes);

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
        per.setMultimedia("none");
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
        adm.setMultimedia("none");
        //adm.setRecibirMensaje(listaMensajes);
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
        
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
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

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public List<Tag> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tag> listaTags) {
        this.listaTags = listaTags;
    }
    
    /*public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
    
    
    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    public void addMessage(Mensaje msg){
        listaMensajes.add(msg);
    }*/
}
