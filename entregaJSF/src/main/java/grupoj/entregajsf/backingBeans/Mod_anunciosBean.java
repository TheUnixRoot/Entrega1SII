/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juanp
 */
@Named(value = "mod_anunciosBean")
@Dependent
public class Mod_anunciosBean implements Serializable{

    @Inject
    PersistenceMock persistencia;
    List<Anuncio> lista;
    Anuncio adv;
    UploadedFile file;
    StreamedContent mul;
    
    /**
     * Creates a new instance of New_anuncioBean
     */
    
    public Mod_anunciosBean () {
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> mapa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        adv = new Anuncio();
        adv.setId(Long.parseLong(mapa.get("id")));
        lista = persistencia.getListaAnuncios();
    }
    
    public long getId() {
        adv = lista.get(
                lista.indexOf(adv));
        return this.adv.getId();
    }
    
    public void setId(long id) {
        adv = lista.get(
                lista.indexOf(adv));
        this.adv.setId(id);
    }
    
    public String getEmpresa() {
        adv = lista.get(
                lista.indexOf(adv));
        return this.adv.getEmpresa();
    }
    
    public void setEmpresa(String empresa) {
        adv = lista.get(
                lista.indexOf(adv));
        this.adv.setEmpresa(empresa);
    }
    
    public Date getFecha_public() {
        adv = lista.get(
                lista.indexOf(adv));
        return this.adv.getFecha_public();
    }
    
    public void setFecha_public(Date fecha_publicacion) {
        adv = lista.get(
                lista.indexOf(adv));
        this.adv.setFecha_public(fecha_publicacion);
    }
    
    public int getDias_contratados() {
        adv = lista.get(
                lista.indexOf(adv));
        return this.adv.getDias_contratados();
    }
    
    public void setDias_contratados(int dias_contratados) {
        adv = lista.get(
                lista.indexOf(adv));
        this.adv.setDias_contratados(dias_contratados);
    }
    
    public boolean isLugar() {
        adv = lista.get(
                lista.indexOf(adv));
        if (this.adv.getLugar() == null)
            return false;
        return this.adv.getLugar().equals("top");
    }
    
    public void setLugar(boolean lugar) {
        adv = lista.get(
                lista.indexOf(adv));
        String res = lugar ? "top":"bot";
        this.adv.setLugar(res);
    }
    
    public StreamedContent getMultimedia() {
        adv = lista.get(
                lista.indexOf(adv));
        return new DefaultStreamedContent(new ByteArrayInputStream(adv.getMultimedia()));
    }
    
    public void setMultimedia(StreamedContent multimedia) {
        multimedia = null;
    }
    
    public UploadedFile getMultimedia2() {
        return file;
    }
    
    public void setMultimedia2(UploadedFile multimedia) {
        adv = lista.get(
                lista.indexOf(adv));
        adv.setMultimedia(multimedia.getContents());
        this.file = multimedia;
    }
    
    public boolean isOnline() {
        adv = lista.get(
                lista.indexOf(adv));
        return this.adv.isOnline();
    }
    
    public void setOnline(boolean online) {
        adv = lista.get(
                lista.indexOf(adv));
        this.adv.setOnline(online);
    }
    
    public String grabar() {
        
        if(adv.isOnline()) {
            for(Anuncio a:lista) {
                if(a.getLugar().equals(adv.getLugar())) {
                    a.setOnline(false);
                }
            }
        } else {
            boolean find = false;
            for(Anuncio a:lista) {
                if(a.isOnline()) {
                    find = true;
                }
            }
            if (!find) {
                adv.setOnline(true);
                FacesContext.getCurrentInstance().addMessage("formu:panel:messages", 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede desactivar el anuncio, pruebe activando otro del mismo lugar" ,
                        "No se puede desactivar el anuncio, pruebe activando otro del mismo lugar"));
            }
        }
        lista.set((lista.indexOf(adv)), adv);
        try {
            System.out.println("Como esta la imagen??? " + adv.getMultimedia().length);
            persistencia.setListaAnuncios(lista);
        } catch (InterruptedException ex) {
            System.err.println("Error al crear anuncio en persistencia " + ex.getMessage());
        }
        
        return "gestion_anuncios.xhtml";
    }
    
}
