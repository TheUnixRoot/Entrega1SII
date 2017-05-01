package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import grupoj.prentrega1.Evento;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author anaes
 */
@Named(value = "verEvento")
@Dependent
public class verEvento {

    
    @Inject
    PersistenceMock persistencia;
    private Evento evento;
    private long id;
    
    
        @PostConstruct
        public void init() {
        Map<String, String> req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.setId(Long.parseLong(req.get("id")));
        Evento ev = new Evento();
        ev.setId(id);
        System.out.println(id);
        if ( this.persistencia.getListaEventos().contains(ev) ) 
            this.evento = this.persistencia.getListaEventos()
                    .get(
                            this.persistencia.getListaEventos().indexOf(ev)
                    );
        else
            this.evento = null;
        ev = null;
    }
    
    /**
     * Creates a new instance of verEvento
     */
    public verEvento() {
    }
    
    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
