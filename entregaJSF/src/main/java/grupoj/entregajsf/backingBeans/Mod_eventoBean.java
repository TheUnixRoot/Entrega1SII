/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Evento;
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
@Named(value = "mod_eventoBean")
@RequestScoped
public class Mod_eventoBean {

    @Inject
    PersistenceMock persistencia;
    private Evento adv;
    private Long ids;

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }
    
    
    
    @PostConstruct
    public void init() {
        Evento add = new Evento();
        add.setId(Long.parseLong(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                .getRequestParameterMap().get("id")
        ));
        adv = persistencia
                .getListaEventos()
                .get(
                persistencia
                .getListaEventos()
                .indexOf(add)
                );
        
    }
    

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Evento getAdv() {
        return adv;
    }

    public void setAdv(Evento adv) {
        this.adv = adv;
    }
    
    
    public String modificarEvento(){
        
       
        
        return "gestion_evento.xhtml";
    
    }
}
