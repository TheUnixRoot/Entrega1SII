/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockingBeans;

import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.TipoNotificacion;
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
    private List<Lugar> listaLugares;
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
        
        listaLugares =new ArrayList<>();
        Lugar lgr1 = new Lugar();
        lgr1.setId(1L);
        lgr1.setNombre("Teatro");
        lgr1.setDescripcion("Lugar donde se realizan obras de teatro");
        listaLugares.add(lgr1);
        Lugar lgr2 = new Lugar();
        lgr1.setId(2L);
        lgr2.setNombre("Campo de futbol");
        lgr2.setDescripcion("Lugar donde se realizan conciertos");
        listaLugares.add(lgr2);
        
        listaEventos =new ArrayList<>();
        Evento ev1 = new Evento();
        ev1.setId(1L);
        ev1.setNombre("concierto estopa");
        ev1.setOcurre_in(lgr2);
        listaEventos.add(ev1);
        
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
     public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }
    
    
    
}
