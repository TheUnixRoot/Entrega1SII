/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.toPDF.PdfCreator;
import grupoj.prentrega1.Evento;
import java.util.Iterator;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author juanp
 */
@Named(value = "printBean")
@RequestScoped
public class printBean {

    @Inject
    private PersistenceMock persistencia;
    private PdfCreator pdf;
    private Evento ev;
    
    /**
     * Creates a new instance of printBean
     */
    public printBean() {
        ev = persistencia.getListaEventos().get(0);
        pdf = new PdfCreator(ev);
    }

    public PdfCreator getPdf() {
        return pdf;
    }

    public void setPdf(PdfCreator pdf) {
        this.pdf = pdf;
    }

    public Evento getEv() {
        return ev;
    }

    public void setEv(Evento ev) {
        this.ev = ev;
    }
    
    public String generar() {
        return pdf.getPath();
    }
    
}
