/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import grupoj.prentrega1.Evento;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
    
    
    
    @PostConstruct
    public void init() {
        evento = this.persistencia.getListaEventos().get(0);
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
    
    
}
