/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;



import grupoj.prentrega1.Evento;
import java.util.List;
import java.util.Map;
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
@Named(value = "verEventoBean")
@RequestScoped
public class verEventoBean {

    @Inject
    private PersistenceMock persistencia;
    
    private List<Evento> listaEventos;
    
    @PostConstruct
    public void init() {
        listaEventos = persistencia.getListaEventos();
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) throws InterruptedException{
        
        persistencia.setListaEventos(listaEventos);
        
    }
    
    
    public String viajar() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "edit_evento.xhtml?id=" + params.get("id");
    }
    
    
    public String viajarv() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "ver_Evento.xhtml?id=" + params.get("id");
    }
    
    public String viajarE() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        return "eliminarEvento.xhtml?id=" + params.get("id");
    }
    
    
}