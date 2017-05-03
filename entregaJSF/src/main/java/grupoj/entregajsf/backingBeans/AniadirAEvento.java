/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author David
 */
@Named(value = "aniadirAEvento")
@Dependent
public class AniadirAEvento {
    @Inject
    private PersistenceMock persistencia;
    @Inject
    private ControlAutorizacion ctrAut;
    
    private Integer valEvento;
    private Integer valLugar;
    
    private String comEvento;
    private String comLugar;
    
    private UploadedFile fEvento;
    private UploadedFile fLugar;
    
    public Integer getValEvento() {
        return valEvento;
    }

    public void setValEvento(Integer valEvento) {
        this.valEvento = valEvento;
    }

    public Integer getValLugar() {
        return valLugar;
    }

    public void setValLugar(Integer valLugar) {
        this.valLugar = valLugar;
    }

    public String getComEvento() {
        return comEvento;
    }

    public void setComEvento(String comEvento) {
        this.comEvento = comEvento;
    }

    public String getComLugar() {
        return comLugar;
    }

    public void setComLugar(String comLugar) {
        this.comLugar = comLugar;
    }
    
    
    
    public String añadirComentarioEvento() {
        
        return null;
    }
    
    public String añadirComentarioLugar() {
        
        return null;
    }
    // Constructor
    public AniadirAEvento() {
    }
    
}
