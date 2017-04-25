/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
@Named(value = "crud_anunciosBean")
@RequestScoped
public class Crud_anunciosBean {

    @Inject
    private PersistenceMock persistencia;
    private Iterable<Anuncio> anuncios;
    /**
     * Creates a new instance of Crud_anunciosBean
     */
    @PostConstruct
    public void init() {
        anuncios = persistencia.getListaAnuncios();
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
    }

    public Iterable<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Iterable<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    
    public StreamedContent getImage(Anuncio adv) {
        StreamedContent result = null;
        try (FileInputStream fstr = new FileInputStream(genPath(adv.getMultimedia()))) {
            result = new DefaultStreamedContent(fstr, "image/png");
        } catch(FileNotFoundException fe) {
        } catch (IOException ex) {
            Logger.getLogger(Crud_anunciosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public String genPath(String path) {
        String newPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/resources/" + path;
        System.out.println(newPath);
        return newPath;
    }
}
