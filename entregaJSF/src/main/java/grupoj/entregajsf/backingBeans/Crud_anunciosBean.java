/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.dropbox.DropboxController;
import grupoj.entregajsf.dropbox.DropboxControllerException;
import grupoj.prentrega1.Anuncio;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "crud_anunciosBean")
@RequestScoped
public class Crud_anunciosBean {

    @Inject
    private PersistenceMock persistencia;
    
    public List<Anuncio> getAnuncios() {
        return persistencia.getListaAnuncios();
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        try {
            this.persistencia.setListaAnuncios(anuncios);
        } catch (InterruptedException ex) {
            Logger.getLogger(Crud_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "edit_anuncio.xhtml?id=" + params.get("id");
    }
    
    public StreamedContent generar(Anuncio adv) {
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }
    
    public String publicar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Anuncio anun = new Anuncio();
        anun.setId(Long.parseLong(params.get("id")));
        List<Anuncio> lista = persistencia.getListaAnuncios();
        int idx = lista.indexOf(anun);
        anun = lista.get(idx);
        System.out.println(anun.getId());
        for(Anuncio a : lista) {
            if (a.getLugar().equals(anun.getLugar())) {
                a.setOnline(false);
            }
        }
        anun.setOnline(true);
        lista.set(idx, anun);
        try {
            persistencia.setListaAnuncios(lista);
        } catch (InterruptedException ex) {
            System.err.println("Error al publicar el anuncio " + ex.getMessage());
        }
        return null;
    }
    
}