package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Usuario;
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
    private ControlAutorizacion control;
    private Usuario usu;
    
    
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
    
    public String meInteresa(){
        Usuario usu = control.getUsuario();
        Map <String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = map.get("id");
        Evento ev = new Evento();
        ev.setId(Long.parseLong(id));
        if(usu == null){
            
        }else{
            ev = persistencia.getListaEventos().get(persistencia.getListaEventos().indexOf(ev));
            usu.getMeInteresa().add(ev);
            ev.getInteresados_at().add(usu);
        }
        return null;
    }
    
    
}
