/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Lugar;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;


/**
 *
 * @author migue
 */
@Named(value = "mod_LugarBean")
@RequestScoped
public class Mod_LugarBean {

    @Inject
    PersistenceMock persistencia;
    private Lugar adv;
    private Long ids;

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }
    
    
    
    @PostConstruct
    public void init() {
        Lugar add = new Lugar();
        add.setId(Long.parseLong(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                .getRequestParameterMap().get("id")
        ));
        adv = persistencia
                .getListaLugares()
                .get(
                persistencia
                .getListaLugares()
                .indexOf(add)
                );
        
    }
    

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Lugar getAdv() {
        return adv;
    }

    public void setAdv(Lugar adv) {
        this.adv = adv;
    }
    
    
    public String modificarEvento(){
        
        
        
        
        
        return "gestion_lugar.xhtml";
    
    }
}
