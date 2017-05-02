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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@Named(value = "mod_anuncioBean")
@RequestScoped
public class Mod_anuncioBean {

    @Inject
    PersistenceMock persistencia;
    Iterator<Anuncio> it;
    private Anuncio adv;
    
    @PostConstruct
    public void init() {
        Anuncio add = new Anuncio();
        add.setId(Long.parseLong(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                .getRequestParameterMap().get("id")
        ));
        adv = persistencia
                .getListaAnuncios()
                .get(
                persistencia
                .getListaAnuncios()
                .indexOf(add)
                );
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Anuncio getAdv() {
        return adv;
    }

    public void setAdv(Anuncio adv) {
        this.adv = adv;
    }
    
   public StreamedContent generar() {
        StreamedContent con = null;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            Anuncio aa = new Anuncio();
            aa.setId(Long.parseLong(params.get("id")));
            String mul = persistencia
                    .getListaUsuarios()
                    .get(persistencia
                            .getListaUsuarios()
                            .indexOf(aa)
                    )
                    .getMultimedia();
            if (mul == null) mul = "/default.jpg";
            con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile(mul))); 
            
        } catch (DropboxControllerException dbex) {
            try {
                con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile("/default.jpg")));
            } catch (DropboxControllerException ex) {
                Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ArrayIndexOutOfBoundsException ie) {
            System.err.println(ie.getMessage() + " id usuario recibido " + params.get("id"));
        } catch (NumberFormatException ne) {
            System.err.println("Error al convertir la id del parametro " + params.get("id") + " excep: " + ne.getMessage());
        }
        return con;
    }
}
