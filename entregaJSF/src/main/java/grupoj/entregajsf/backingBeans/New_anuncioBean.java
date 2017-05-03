/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juanp
 */
@Named(value = "new_anuncioBean")
@RequestScoped
public class New_anuncioBean {

    @Inject
    PersistenceMock persistencia;
    Anuncio adv;
    UploadedFile file;
    
    /**
     * Creates a new instance of New_anuncioBean
     */
    public New_anuncioBean() {
        adv = new Anuncio();
    }
    
    public long getId() {
        return this.adv.getId();
    }
    
    public void setId(long id) {
        this.adv.setId(id);
    }
    
    public String getEmpresa() {
        return this.adv.getEmpresa();
    }
    
    public void setEmpresa(String empresa) {
        this.adv.setEmpresa(empresa);
    }
    
    public Date getFecha_public() {
        return this.adv.getFecha_public();
    }
    
    public void setFecha_public(Date fecha_publicacion) {
        this.adv.setFecha_public(fecha_publicacion);
    }
    
    public int getDias_contratados() {
        return this.adv.getDias_contratados();
    }
    
    public void setDias_contratados(int dias_contratados) {
        this.adv.setDias_contratados(dias_contratados);
    }
    
    public boolean isLugar() {
        if (this.adv.getLugar() == null)
            return false;
        return this.adv.getLugar().equals("top");
    }
    
    public void setLugar(boolean lugar) {
        String res = lugar ? "top":"bot";
        this.adv.setLugar(res);
    }
    
    public UploadedFile getMultimedia() {
        return file;
    }
    
    public void setMultimedia(UploadedFile multimedia) {
        adv.setMultimedia(multimedia.getContents());
        this.file = multimedia;
    }
    
    public boolean isOnline() {
        return this.adv.isOnline();
    }
    
    public void setOnline(boolean online) {
        this.adv.setOnline(online);
    }
    
    public String grabar() {
        List<Anuncio> lista = persistencia.getListaAnuncios();
        
        if(adv.isOnline()) {
            for(Anuncio a:lista) {
                if(a.getLugar().equals(adv.getLugar())) {
                    a.setOnline(false);
                }
            }
        }
        adv.setId(System.currentTimeMillis());
        lista.add(adv);
        try {
            System.out.println("Como esta la imagen??? " + adv.getMultimedia().length);
            persistencia.setListaAnuncios(lista);
        } catch (InterruptedException ex) {
            System.err.println("Error al crear anuncio en persistencia " + ex.getMessage());
        }
        
        return "gestion_anuncios.xhtml";
    }
    
}
