/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "show_anunciosBean")
@RequestScoped
public class Show_anunciosBean {
    
    @Inject
    PersistenceMock persistencia;

    public Show_anunciosBean() {
        
    }
    
    public StreamedContent getTop() {
        Iterator<Anuncio> it = persistencia.getListaAnuncios().iterator();
        Anuncio adv = null;
        boolean find = false;
        while(it.hasNext() && !find) {
            adv = it.next();
            if (adv.getLugar().equals("top") && adv.isOnline())
                find = true;
        }
        if(!find) {
            System.out.println("NO encuentra anuncio en top");
            return null;
        }
        
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }
    
    public StreamedContent getBottom() {
        Iterator<Anuncio> it = persistencia.getListaAnuncios().iterator();
        Anuncio adv = null;
        boolean find = false;
        while(it.hasNext() && !find) {
            adv = it.next();
            if (adv.getLugar().equals("bot") && adv.isOnline())
                find = true;
        }
        if(!find) {
            return null;
        }
        
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }
    
    public StreamedContent getSelf() {
        Iterator<Anuncio> it = persistencia.getListaAnuncios().iterator();
        Anuncio adv = null;
        boolean find = false;
        while(it.hasNext() && !find) {
            adv = it.next();
            if (adv.getLugar().equals("self") && adv.isOnline())
                find = true;
        }
        if(!find) {
            return null;
        }
        
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }
}
