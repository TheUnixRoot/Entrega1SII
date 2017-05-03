package grupoj.entregajsf.backingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.entregajsf.toPDF.PdfCreator;
import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Usuario;
import java.io.ByteArrayInputStream;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private Map<String, String> req;
    private PdfCreator pdf;
    
        @PostConstruct
        public void init() {
        
        req = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
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
    
    public StreamedContent getMultimedia() {
        if (evento.getMultimedia() == null) {
            return null;
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(evento.getMultimedia()));
    }
    
    public void setMultimedia(StreamedContent mult){
        
    }
    
    public String meInteresa(){
        usu = control.getUsuario();
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
    
    public PdfCreator getPdf() {
        return pdf;
    }

    public void setPdf(PdfCreator pdf) {
        this.pdf = pdf;
    }
    
    public StreamedContent getFile() {
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        Evento evprima = new Evento();
//        evprima.setId(Long.parseLong(params.get("id")));
//        this.evento = persistencia.getListaEventos().get(
//            persistencia.getListaEventos().indexOf(evprima));
//        
        pdf = new PdfCreator(this.evento);
        
        StreamedContent stc = new DefaultStreamedContent(
                new ByteArrayInputStream(pdf.getStream()), 
                "application/pdf", evento.getNombre() + ".pdf");
        return stc;
    }
}
