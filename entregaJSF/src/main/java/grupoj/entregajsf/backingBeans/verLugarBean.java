/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;



import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Usuario;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;


/**
 *
 * @author migue
 */
@Named(value = "verLugarBean")
@RequestScoped
public class verLugarBean {

    @Inject
    private PersistenceMock persistencia;
    
    private List<Lugar> listaLugares;
    
    @PostConstruct
    public void init() {
        listaLugares = persistencia.getListaLugares();
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException{
        //this.listaLugares = listaLugares;
        persistencia.setListaLugares(listaLugares);
    }
    
    
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "edit_lugar.xhtml?id=" + params.get("id");
    }
    
    
    public String viajarv() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "verLugar.xhtml?id=" + params.get("id");
    }
    
    public String viajarE() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "eliminarLugar.xhtml?id=" + params.get("id");
    }
    
    
}