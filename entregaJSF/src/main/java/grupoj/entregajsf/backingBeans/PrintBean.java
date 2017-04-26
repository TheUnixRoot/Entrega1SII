/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.toPDF.PdfCreator;
import grupoj.prentrega1.Evento;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "printBean")
@RequestScoped
public class PrintBean implements Serializable {

    @Inject
    private PersistenceMock persistencia;
    private PdfCreator pdf;
    private Evento ev;
    
    /**
     * Creates a new instance of printBean
     */
    @PostConstruct
    public void init() {
        ev = null;
    }

    public Evento getEv() {
        return ev;
    }

    public void setEv(Evento ev) {
        this.ev = ev;
    }
    
    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public PdfCreator getPdf() {
        return pdf;
    }

    public void setPdf(PdfCreator pdf) {
        this.pdf = pdf;
    }
    
    public StreamedContent getFile() {
        setEv(persistencia.getListaEventos().get(0));
        pdf = new PdfCreator(this.ev);
        
        StreamedContent stc = new DefaultStreamedContent(
                new ByteArrayInputStream(pdf.getStream()), 
                "application/pdf", ev.getNombre() + ".pdf");
        return stc;
    }
    
}
