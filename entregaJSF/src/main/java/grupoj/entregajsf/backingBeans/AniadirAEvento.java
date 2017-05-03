/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Usuario;
import grupoj.prentrega1.Valoracion_eve;
import grupoj.prentrega1.Valoracion_lug;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author David
 */
@Named(value = "aniadirAEvento")
@RequestScoped
public class AniadirAEvento {
    @Inject
    private ControlAutorizacion ctrAut;
    @Inject 
    private PersistenceMock persistencia;
    
    // En las clases ya los tengo
    private Evento eve;
    private Integer valEvento;
    private String comEvento;
    private UploadedFile fEvento;  
    private List<Valoracion_eve> listEve;
    
    private Lugar lug;
    private Integer valLugar;
    private String comLugar;
    private UploadedFile fLugar;
    private List<Valoracion_lug> listLug;

    @PostConstruct
    public void init() {
        eve = new Evento();
        String ide = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if(ide != null) {
            eve.setId(Long.parseLong(ide));
            eve = persistencia.getListaEventos()
                    .get(
                    persistencia.getListaEventos().indexOf(eve));
        }
    }
    
    public Integer getValEvento() {
        return valEvento;
    }

    public void setValEvento(Integer valEvento) {
        this.valEvento = valEvento;
    }

    public String getComEvento() {
        return comEvento;
    }

    public void setComEvento(String comEvento) {
        this.comEvento = comEvento;
    }

    public UploadedFile getfEvento() {
        return fEvento;
    }

    public void setfEvento(UploadedFile fEvento) {
        this.fEvento = fEvento;
    }

    public Integer getValLugar() {
        return valLugar;
    }

    public void setValLugar(Integer valLugar) {
        this.valLugar = valLugar;
    }

    public String getComLugar() {
        return comLugar;
    }

    public void setComLugar(String comLugar) {
        this.comLugar = comLugar;
    }

    public UploadedFile getfLugar() {
        return fLugar;
    }

    public void setfLugar(UploadedFile fLugar) {
        this.fLugar = fLugar;
    }

    
    public String añadirComentarioEvento() {
        Usuario usu = this.ctrAut.getUsuario(); // Usuario logueado
        
        listEve = eve.getValoraciones_sobre();
        
        if (listEve == null) {
            listEve = new ArrayList<>();
        }
        
        if (comEvento == null) {
            comEvento = " ";
        }
        if (valEvento == null) {
            valEvento = 3;
        }
        
        
        Valoracion_eve valEve = new Valoracion_eve();
        valEve.setId(System.currentTimeMillis());
        valEve.setCalificacion(valEvento);
        valEve.setComentario(comEvento);
        if (fEvento == null) {
            valEve.setFotos(new byte[1]);
        } else {
            valEve.setFotos(fEvento.getContents());
        }
        valEve.setRealizado_por(usu);
        valEve.setValoracion_sobre(eve);
        
        listEve.add(valEve);
        eve.setValoraciones_sobre(listEve);
        
        valEvento = null;
        valEvento = null;
        fEvento = null;
        
        return null;
    }
    
    public String añadirComentarioLugar() {
        lug = eve.getOcurre_in(); // Lo cojo de la página que ya tiene uno
        Usuario usu = this.ctrAut.getUsuario(); // Usuario logueado
        
        listLug = lug.getValoraciones_sobre();
        
        if (listLug == null) {
            listLug = new ArrayList<>();
        }
        if (comLugar == null) {
            comLugar = " ";
        }
        if (valLugar == null) {
            valLugar = 3;
        }
        
        
         Valoracion_lug valLug = new Valoracion_lug();
        valLug.setId(System.currentTimeMillis());
        valLug.setCalificacion(valLugar);
        valLug.setComentario(comLugar);
        if (fEvento == null) {
            valLug.setFotos(new byte[1]);
        } else {
            valLug.setFotos(fLugar.getContents());
        }
        valLug.setRealizado_por(usu);
        valLug.setValoracion_sobre(lug);
        listLug.add(valLug);
        lug.setValoraciones_sobre(listLug);
        
        valLugar = null;
        comLugar = null;
        fLugar = null;
        
        return null;
    }
    
}
