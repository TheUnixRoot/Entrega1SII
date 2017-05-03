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
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
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
        System.out.println(this.comEvento);
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
        eve = new Evento(); // Lo cojo de la página que ya tiene uno
        Usuario usu = this.ctrAut.getUsuario(); // Usuario logueado
        
        listEve = eve.getValoraciones_sobre();
        
        if (listEve == null) {
            listEve = new ArrayList<>();
        }
        
        Valoracion_eve valEve = new Valoracion_eve();
        valEve.setId(System.currentTimeMillis());
        valEve.setCalificacion(valEvento);
        valEve.setComentario(comEvento);
        valEve.setFotos(fEvento.getContents());
        valEve.setRealizado_por(usu);
        valEve.setValoracion_sobre(eve);
        
        listEve.add(valEve);
        eve.setValoraciones_sobre(listEve);
        
        return null;
    }
    
    public String añadirComentarioLugar() {
        
        return null;
    }
    
    public AniadirAEvento() {
        this.comEvento = "";
        this.comLugar = "";
        this.valEvento = null;
        this.valLugar = null;
        this.fEvento = null;
        this.fLugar = null;
    }
    
}
