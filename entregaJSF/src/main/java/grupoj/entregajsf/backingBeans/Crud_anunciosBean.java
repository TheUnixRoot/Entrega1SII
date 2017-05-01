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
    private Iterator<Anuncio> it;
    
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
    
    public StreamedContent generar() {
        StreamedContent con = null;
        try {
            if(it == null) {
                it = persistencia.getListaAnuncios().iterator();
            } else if (it.hasNext()) {
                String mul = it.next().getMultimedia();
                if (mul == null) mul = "/default.jpg";
                con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile(mul))); 
            }
        } catch (DropboxControllerException dbex) {
            try {
                con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile("/default.jpg")));
            } catch (DropboxControllerException ex) {
                Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IndexOutOfBoundsException ie) {
            Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ie);
        }
        return con;
    }
    
    public void publicar() {
        
    }
    
}
